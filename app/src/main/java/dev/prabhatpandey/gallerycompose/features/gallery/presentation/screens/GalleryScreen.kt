package dev.prabhatpandey.gallerycompose.features.gallery.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.prabhatpandey.gallerycompose.core.presentation.theme.GalleryComposeTheme
import dev.prabhatpandey.gallerycompose.features.gallery.domain.ImageModel
import dev.prabhatpandey.gallerycompose.features.gallery.presentation.components.ImageItem
import timber.log.Timber

@Composable
fun GalleryScreen(
    onNavigateToDetails: (Int) -> Unit,
    images: List<ImageModel>,
    onScrolledToEnd: (() -> Unit)? = null
) {
    Timber.d("GalleryScreen: Composing")
    val listState = rememberLazyGridState()

    val isScrolledToEnd by remember {
        derivedStateOf {
            listState.isScrolledToEnd()
        }
    }

    LaunchedEffect(isScrolledToEnd) {
        Timber.d("GalleryScreen: Scrolled to End ===> ")
        if(isScrolledToEnd) onScrolledToEnd?.invoke()
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = listState
        ) {
            items(
                images.size,
                key = { it }
            ) { index ->
                ImageItem(
                    image = images[index],
                    onTapped = {
                        onNavigateToDetails(index)
                    }
                )
            }
        }
    }

}

@Preview
@Composable
fun GalleryScreenPreview() {
    GalleryComposeTheme {
        GalleryScreen(
            onNavigateToDetails = {},
            images = emptyList()
        )
    }
}

fun LazyGridState.isScrolledToEnd() : Boolean {
    if (layoutInfo.totalItemsCount <= 0) return false
    return layoutInfo.visibleItemsInfo.lastOrNull()?.index?.let { last ->
        last >= layoutInfo.totalItemsCount - 5
    } ?: false
}