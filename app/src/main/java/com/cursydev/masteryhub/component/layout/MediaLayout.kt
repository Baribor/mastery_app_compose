package com.cursydev.masteryhub.component.layout

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cursydev.masteryhub.util.nav.BottomNav
import com.cursydev.masteryhub.util.nav.BottomNavLayout
import com.cursydev.masteryhub.util.nav.Toolbar
import com.cursydev.masteryhub.util.nav.ToolbarActionsData
import com.cursydev.masteryhub.util.nav.ToolbarLayout
import com.cursydev.masteryhub.component.ui.MediaViewModel
import com.cursydev.masteryhub.screens.Screen
import com.cursydev.masteryhub.screens.media.MusicScreen
import com.cursydev.masteryhub.screens.media.VideoScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaLayout(onExternalNav: (route: String)->Unit) {

    val controller = rememberNavController()
    val mediaViewModel = viewModel<MediaViewModel>()

    Scaffold(
        topBar = {
            Toolbar.BlogScreenToolbar.ToolbarLayout(actionsData = ToolbarActionsData(
                onNavClick = {
                    onExternalNav(Screen.HomeScreen.route)
                }
            ), mediaViewModel.toolbarTitle.value)
        },

        bottomBar = {
            BottomNav.MediaScreenBottomNav.BottomNavLayout(controller, changeTitle = {
                mediaViewModel.setTitle(it)
            })
        }
    ) {

        Surface(modifier = Modifier.padding(it)) {
            NavHost(navController = controller, startDestination = Screen.MediaScreens.VideoScreen.route){

                composable(route= Screen.MediaScreens.VideoScreen.route){
                    VideoScreen()
                }

                composable(route= Screen.MediaScreens.MusicScreen.route){
                    MusicScreen()
                }

            }
        }
    }
}