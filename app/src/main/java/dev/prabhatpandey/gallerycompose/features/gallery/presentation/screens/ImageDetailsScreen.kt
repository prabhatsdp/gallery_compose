package dev.prabhatpandey.gallerycompose.features.gallery.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import dev.prabhatpandey.gallerycompose.R
import dev.prabhatpandey.gallerycompose.core.presentation.theme.GalleryComposeTheme
import dev.prabhatpandey.gallerycompose.features.gallery.domain.ImageModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageDetailsScreen(
    position: Int? = null,
    images: List<ImageModel>,
    onBackPressed: (() -> Unit)? = null
) {
    val imageModel = position?.let {
        images.getOrNull(position)
    }

    Scaffold {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            GlideImage(
                imageModel = { imageModel?.thumbUrl },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center
                ),
                modifier = Modifier.fillMaxSize(),
                previewPlaceholder = R.drawable.ic_launcher_foreground,
                failure = {

                },
                loading = {

                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewImageDetails() {
    GalleryComposeTheme {
        ImageDetailsScreen(position = 0, images = emptyList())
    }
}