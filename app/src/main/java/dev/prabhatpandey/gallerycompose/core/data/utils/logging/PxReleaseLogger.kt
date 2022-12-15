package dev.prabhatpandey.gallerycompose.core.data.utils.logging

import android.util.Log
import timber.log.Timber

/**
 * To be implemented for release logging
 * [Pass your remote logging service e.g. Crashlytics etc. as constructor arguments in this class]
 */
class PxReleaseLogger(): Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        when(priority) {
            Log.VERBOSE -> {

            }
            Log.DEBUG -> {
            }
            Log.INFO -> {
            }
            Log.WARN -> {
            }
            Log.ASSERT -> {
            }
            Log.ERROR -> {
            }
        }
    }

}