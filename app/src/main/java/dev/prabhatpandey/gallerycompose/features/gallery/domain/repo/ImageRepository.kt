package dev.prabhatpandey.gallerycompose.features.gallery.domain.repo

import dev.prabhatpandey.gallerycompose.features.gallery.domain.ImageModel

interface ImageRepository {
    suspend fun getPhotos(clientId: String, pageNumber: Int, pageLength: Int) : Result<List<ImageModel>>
}