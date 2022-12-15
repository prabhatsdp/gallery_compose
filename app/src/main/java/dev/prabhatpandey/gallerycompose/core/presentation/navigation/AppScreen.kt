package dev.prabhatpandey.gallerycompose.core.presentation.navigation

import android.content.Context
import dev.prabhatpandey.gallerycompose.R

sealed class AppScreen(val route: String) {
    object Gallery: AppScreen("gallery")
    object ImageDetail: AppScreen("image_detail")

    fun withArgs(vararg args: String) : String {
        return buildString {
            append(route)
            args.forEach { append("/$it")}
        }
    }



    companion object {
        @JvmStatic
        fun getTitle(routeStr: String, context: Context) : String {
            return when {
                routeStr.startsWith(Gallery.route) -> context.getString(R.string.gallery)
                routeStr.startsWith(ImageDetail.route) -> context.getString(R.string.image_details)
                else -> ""
            }
        }
    }

}