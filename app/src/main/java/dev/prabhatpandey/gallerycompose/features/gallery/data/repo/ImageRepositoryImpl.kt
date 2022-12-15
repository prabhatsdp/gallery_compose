package dev.prabhatpandey.gallerycompose.features.gallery.data.repo

import dev.prabhatpandey.gallerycompose.core.data.remote.RemoteApi
import dev.prabhatpandey.gallerycompose.core.data.utils.extentions.toDomainError
import dev.prabhatpandey.gallerycompose.features.gallery.domain.ImageModel
import dev.prabhatpandey.gallerycompose.features.gallery.domain.repo.ImageRepository
import dev.prabhatpandey.retrofitadapter.NetworkResponse
import javax.inject.Inject


class ImageRepositoryImpl @Inject constructor(
    private val api: RemoteApi
) : ImageRepository {
    override suspend fun getPhotos(clientId: String, pageNumber: Int, pageLength: Int): Result<List<ImageModel>> {
        return when(val res = api.getPhotos(clientId, page = pageNumber, perPage = pageLength)) {
            is NetworkResponse.Success -> {
                Result.success(res.body.map { it.asDomainModel() })
            }
            else -> {
                Result.failure(res.toDomainError())
            }
        }
    }
}