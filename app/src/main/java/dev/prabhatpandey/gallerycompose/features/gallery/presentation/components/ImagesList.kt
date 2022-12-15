package dev.prabhatpandey.gallerycompose.features.gallery.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.prabhatpandey.gallerycompose.features.gallery.domain.ImageModel
import dev.prabhatpandey.gallerycompose.features.gallery.presentation.interfaces.ItemClickListener

@Composable
fun ImagesList(
    images: List<ImageModel>,
    onItemTapped: ItemClickListener
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(images.size) { index ->
                ImageItem(
                    image = images[index],
                    onTapped = {
                        onItemTapped(index)
                    }
                )
            }
        }
    }
}