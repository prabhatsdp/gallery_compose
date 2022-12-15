package dev.prabhatpandey.gallerycompose.features.gallery.presentation.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.*
import com.google.accompanist.navigation.animation.composable
import dev.prabhatpandey.gallerycompose.core.presentation.navigation.AppScreen
import dev.prabhatpandey.gallerycompose.features.gallery.domain.ImageModel
import dev.prabhatpandey.gallerycompose.features.gallery.presentation.screens.GalleryScreen
import dev.prabhatpandey.gallerycompose.features.gallery.presentation.screens.ImageDetailsScreen
import dev.prabhatpandey.gallerycompose.features.gallery.presentation.viewmodels.GalleryViewModel

private const val POSITION_ARG = "position"

fun NavController.navigateToImageDetails(position: Int, navOptions: NavOptions? = null) {
    this.navigate(AppScreen.ImageDetail.withArgs(position.toString()), navOptions)
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalLifecycleComposeApi::class)
fun NavGraphBuilder.galleryScreen(navController: NavHostController) {
    composable(AppScreen.Gallery.route) { backStackEntry ->
        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry("parent")
        }
        val galleryViewModel: GalleryViewModel = hiltViewModel(parentEntry)
        val images: List<ImageModel> by galleryViewModel.images.collectAsStateWithLifecycle()
        GalleryScreen(
            images = images,
            onNavigateToDetails = {
                navController.navigateToImageDetails(position = it)
            },
            onScrolledToEnd = {
                galleryViewModel.loadNextPage()
            }
        )
    }
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalLifecycleComposeApi::class)
fun NavGraphBuilder.imageDetailsScreen(navController: NavHostController) {
    composable(
        route = "${AppScreen.ImageDetail.route}/{$POSITION_ARG}",
        arguments = listOf(navArgument(POSITION_ARG) { type = NavType.IntType })
    ) { backStackEntry ->
        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry("parent")
        }
        val galleryViewModel: GalleryViewModel = hiltViewModel(parentEntry)
        val images: List<ImageModel> by galleryViewModel.images.collectAsStateWithLifecycle()
        val position = backStackEntry.arguments?.getInt("position")
        ImageDetailsScreen(
            position = position,
            images = images
        )
    }
}