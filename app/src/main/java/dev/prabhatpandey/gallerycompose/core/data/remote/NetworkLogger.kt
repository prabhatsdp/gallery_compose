package dev.prabhatpandey.gallerycompose.core.data.remote

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

class NetworkLogger : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        val logName = "NetworkCall"
        if (message.startsWith("{") || message.startsWith("[")) {
            try {
                val prettyPrintJson = GsonBuilder().setPrettyPrinting()
                    .create().toJson(JsonParser().parse(message))
                Timber.tag(logName)
                    .i("╔═════════════════════════════════════════════════════════════════════════════════════")
                // INFO: If you want to print each line in a new line then uncomment
                // below code
//                prettyPrintJson.lines().forEach {
//                    Timber.tag(logName).i("╟─ $it")
//                }
                Timber.tag(logName).i(prettyPrintJson)
                Timber.tag(logName)
                    .i("╚═════════════════════════════════════════════════════════════════════════════════════")
            } catch (m: JsonSyntaxException) {
                Timber.tag(logName).i(message = "╟─ $message")
            } catch (e: Exception) {
                Timber.tag(logName).i(message = "╟─ $message")
            }
        } else {
            Timber.tag(logName).i(message = "╟─ $message")
        }
    }
}