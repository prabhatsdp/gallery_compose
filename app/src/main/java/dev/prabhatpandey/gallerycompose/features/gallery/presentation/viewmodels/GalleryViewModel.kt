package dev.prabhatpandey.gallerycompose.features.gallery.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prabhatpandey.gallerycompose.BuildConfig
import dev.prabhatpandey.gallerycompose.core.data.remote.DataConsts.CLIENT_ID
import dev.prabhatpandey.gallerycompose.features.gallery.domain.ImageModel
import dev.prabhatpandey.gallerycompose.features.gallery.domain.usecases.GetImagesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase
): ViewModel() {

    private val _images = MutableStateFlow<List<ImageModel>>(emptyList())
    val images = _images.asStateFlow()

    private var pageNumber = 1
    private var isLoading = false
    private var isFinished = false

    init {
        loadImages()
    }

    fun loadNextPage() {
        if (isLoading || isFinished) return
        isLoading = true
        loadImages()
    }

    private fun loadImages() {
        viewModelScope.launch(Dispatchers.IO) {
            getImagesUseCase(
                BuildConfig.CLIENT_ID,
                pageNumber = pageNumber,
                pageLength = PAGE_LENGTH
            )
                .onSuccess { list ->
                    Timber.d("loadImages: Images ==> $list")
                    if (list.size < PAGE_LENGTH) {
                        isFinished = true
                    }
                    _images.update {
                        it.toMutableList().apply {
                            addAll(list)
                        }
                    }
                    pageNumber++
                    isLoading = false
                }
                .onFailure {
                    Timber.e(it, "loadImages: ${it.message}")
                    isLoading = false
                }
        }
    }

    companion object {
        private const val PAGE_LENGTH = 10
        private const val TAG = "GalleryViewModel"
    }

}