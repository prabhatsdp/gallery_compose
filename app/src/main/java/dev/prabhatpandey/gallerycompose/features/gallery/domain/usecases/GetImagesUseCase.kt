package dev.prabhatpandey.gallerycompose.features.gallery.domain.usecases

import dagger.hilt.android.scopes.ViewModelScoped
import dev.prabhatpandey.gallerycompose.features.gallery.domain.ImageModel
import dev.prabhatpandey.gallerycompose.features.gallery.domain.repo.ImageRepository
import javax.inject.Inject

@ViewModelScoped
class GetImagesUseCase @Inject constructor(
    private val imageRepo: ImageRepository
) {
    suspend operator fun invoke(clientId: String, pageNumber: Int, pageLength: Int) : Result<List<ImageModel>> {
        return imageRepo.getPhotos(clientId, pageNumber = pageNumber, pageLength = pageLength)
    }
}