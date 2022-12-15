package dev.prabhatpandey.gallerycompose.features.gallery.presentation.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import dev.prabhatpandey.gallerycompose.R
import dev.prabhatpandey.gallerycompose.features.gallery.domain.ImageModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageItem(
    image: ImageModel,
    modifier: Modifier = Modifier,
    onTapped: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.fillMaxWidth().padding(16.dp).aspectRatio(1F),
        onClick = {
            onTapped()
        }
    ) {
        GlideImage(
            imageModel = { image.thumbUrl },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
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


@Preview
@Composable
fun PreviewImageItem() {
    ImageItem(
        image = ImageModel(
            "1",
            10,
            "https://images.unsplash.com/photo-1670768563220-c13cfa7e1dbc?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=Mnw4NjM2MHwwfDF8YWxsfDN8fHx8fHwyfHwxNjcwODU0MzA1&ixlib=rb-4.0.3&q=80&w=200",
            "https://images.unsplash.com/photo-1670768563220-c13cfa7e1dbc?crop=entropy&cs=tinysrgb&fm=jpg&ixid=Mnw4NjM2MHwwfDF8YWxsfDN8fHx8fHwyfHwxNjcwODU0MzA1&ixlib=rb-4.0.3&q=80"
        ),
        onTapped = {},
    )
}