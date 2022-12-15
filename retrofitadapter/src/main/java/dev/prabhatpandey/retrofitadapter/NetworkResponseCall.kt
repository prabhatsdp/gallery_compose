package dev.prabhatpandey.retrofitadapter

import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

internal class NetworkResponseCall<S : Any, E : Any>(
    private val delegate: Call<S>,
    private val errorConverter: Converter<ResponseBody, E>,
) : Call<NetworkResponse<S, E>> {

    override fun enqueue(callback: Callback<NetworkResponse<S, E>>) {
        return delegate.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()

                if (response.isSuccessful) {
                    body?.let {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.Success(it, response))
                        )
                    } ?: run {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.UnknownError(null, response))
                        )
                    }
                } else {
                    val errorBody = when {
                        error == null -> null
                        error.contentLength() == 0L -> null
                        else -> try {
                            errorConverter.convert(error)
                        } catch (ex: Exception) {
                            null
                        }
                    }
                    errorBody?.let {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.ApiError(it, code))
                        )
                    } ?: run {
                        handleResponseWithoutErrorBody(code, callback, response)
                    }
                }
            }

            override fun onFailure(call: Call<S>, throwable: Throwable) {
                val networkResponse = when (throwable) {
                    is IOException -> NetworkResponse.NetworkError(throwable)
                    else -> NetworkResponse.UnknownError(throwable, null)
                }
                callback.onResponse(this@NetworkResponseCall, Response.success(networkResponse))
            }
        })
    }

    private fun handleResponseWithoutErrorBody(
        code: Int,
        callback: Callback<NetworkResponse<S, E>>,
        response: Response<S>
    ) {
        when {
            code == 401 -> {
                callback.onResponse(
                    this@NetworkResponseCall,
                    Response.success(NetworkResponse.AuthError(null))
                )
            }
            code >= 500 -> {

                callback.onResponse(
                    this@NetworkResponseCall,
                    Response.success(NetworkResponse.ServerError(null, response))
                )

            }
            else -> {
                callback.onResponse(
                    this@NetworkResponseCall,
                    Response.success(NetworkResponse.UnknownError(null, response))
                )
            }
        }
    }

    override fun isExecuted(): Boolean =  synchronized(this) { delegate.isExecuted }

    override fun clone() = NetworkResponseCall(delegate.clone(), errorConverter)

    override fun isCanceled(): Boolean =  synchronized(this) { delegate.isCanceled }

    override fun cancel() = synchronized(this) { delegate.cancel() }

    override fun execute(): Response<NetworkResponse<S, E>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}