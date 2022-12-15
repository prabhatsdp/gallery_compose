package dev.prabhatpandey.gallerycompose.core.presentation.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import dev.prabhatpandey.gallerycompose.features.gallery.presentation.nav.galleryScreen
import dev.prabhatpandey.gallerycompose.features.gallery.presentation.nav.imageDetailsScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RootNavGraph(
    navController: NavHostController,
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = AppScreen.Gallery.route,
        route = "parent",
        enterTransition = { slideInHorizontally(tween()) { it } },
        exitTransition = { slideOutHorizontally(tween()) { -it } },
        popEnterTransition = { slideInHorizontally(tween()) { -it } },
        popExitTransition = { slideOutHorizontally(tween()) { it } }
    ) {
        galleryScreen(navController)
        imageDetailsScreen(navController)
    }
}

