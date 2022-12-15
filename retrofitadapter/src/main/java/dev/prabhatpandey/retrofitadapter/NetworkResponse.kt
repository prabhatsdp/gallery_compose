package dev.prabhatpandey.retrofitadapter

import okhttp3.Headers
import retrofit2.Response
import java.io.IOException

public sealed interface NetworkResponse<out S : Any, out E : Any> {
    /**
     * Success response with body
     */
    data class Success<S : Any>(
        public val body: S, public val response: Response<*>
    ) : NetworkResponse<S, Nothing> {

        /**
         * The status code returned by server with the [response]
         */
        public val code: Int
            get() = response.code()

        /**
         * The headers returned by server with [response]
         */
        public val headers: Headers
            get() = response.headers()
    }

    /**
     * The response type to handle failure of the network request
     */
    public sealed interface Error<S : Any, E : Any> : NetworkResponse<S, E> {
        /**
         * The body of the failed network request, if not available then null
         */
        public val body: E?

        /**
         * The error throwable of the failed network request, if not available then null
         */
        public val error: Throwable?
    }


    /**
     * Failure response with body
     */
    data class ApiError<E : Any>(val body: E, val code: Int) : NetworkResponse<Nothing, E>

    /**
     * Network error (Not Connected etc.)
     */
    data class NetworkError(val error: IOException) : NetworkResponse<Nothing, Nothing>

    /**
     * Authentication Error (Token expired, incorrect credentials etc.)
     *      - Specially to handle refresh token logic, logout user, show login again message
     *      or something like that
     */
    data class AuthError(val error: Throwable?) : NetworkResponse<Nothing, Nothing>


    /**
     * Server error Response code 5xx and above
     * (server crash, failed or not working etc.)
     */
    public data class ServerError<S : Any, E : Any>(
        public override val body: E?,
        public val response: Response<*>?
    ) : Error<S, E> {

        /**
         * The status code returned by server with the [response]
         */
        public val code: Int?
            get() = response?.code()

        /**
         * The headers returned by server with [response]
         */
        public val headers: Headers?
            get() = response?.headers()


        /**
         * error is always `null` for [ServerError]
         */
        override val error: Throwable?
            get() = null
    }


    /**
     * For example, json parsing error
     */
    public data class UnknownError<S : Any, E : Any>(
        public override val error: Throwable?,
        public val response: Response<*>?
    ) : Error<S, E> {

        /**
         * body is always `null` for an [UnknownError]
         */
        override val body: E?
            get() = null


        /**
         * The status code returned by server with the [response], if available
         */
        public val code: Int?
            get() = response?.code()

        /**
         * The headers returned by server with [response], if available
         */
        public val headers: Headers?
            get() = response?.headers()


    }


}