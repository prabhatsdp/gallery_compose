package dev.prabhatpandey.gallerycompose.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dev.prabhatpandey.gallerycompose.BuildConfig
import dev.prabhatpandey.gallerycompose.core.data.utils.logging.PxDebugLogger
import dev.prabhatpandey.gallerycompose.core.data.utils.logging.PxReleaseLogger
import timber.log.Timber

@HiltAndroidApp
class GalleryComposeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        configureTimberForLogging()
    }

    private fun configureTimberForLogging() {
        if (BuildConfig.DEBUG) Timber.plant(PxDebugLogger()) else Timber.plant(PxReleaseLogger())
    }
}
