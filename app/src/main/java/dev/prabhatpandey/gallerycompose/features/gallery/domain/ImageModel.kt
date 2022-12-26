package dev.prabhatpandey.gallerycompose.features.gallery.domain

data class ImageModel(
    val id: String,
    val likes: Int,
    val thumbUrl: String,
    val fullUrl: String,
    val description: String = ""
)
