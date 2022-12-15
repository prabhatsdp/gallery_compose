package dev.prabhatpandey.gallerycompose.features.gallery.presentation.interfaces

fun interface ItemClickListener {
    operator fun invoke(position: Int)
}