package dev.prabhatpandey.gallerycompose.core.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dev.prabhatpandey.gallerycompose.core.presentation.navigation.AppScreen
import dev.prabhatpandey.gallerycompose.core.presentation.navigation.RootNavGraph
import dev.prabhatpandey.gallerycompose.core.presentation.theme.GalleryComposeTheme
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RootScreen() {
    val navController = rememberAnimatedNavController()
    var toolbarTitle: String by remember {
        mutableStateOf("")
    }
    var isBackButtonVisible by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = toolbarTitle)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                navigationIcon = {
                    if (isBackButtonVisible) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                painter = rememberVectorPainter(image = Icons.Rounded.ArrowBack),
                                contentDescription = "Back"
                            )
                        }
                    }
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            RootNavGraph(navController = navController)
        }
    }

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collectLatest { backStackEntry ->
            toolbarTitle = AppScreen.getTitle(backStackEntry.destination.route.orEmpty(), context)
            isBackButtonVisible = navController.previousBackStackEntry != null
        }
    }


}

@Preview
@Composable
fun RootScreenPreview() {
    GalleryComposeTheme {
        RootScreen()
    }
}