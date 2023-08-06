package com.cursydev.masteryhub

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cursydev.masteryhub.component.nav.Toolbar
import com.cursydev.masteryhub.component.nav.ToolbarActionsData
import com.cursydev.masteryhub.component.nav.graph.blogGraph
import com.cursydev.masteryhub.component.ui.GeneralViewModel
import com.cursydev.masteryhub.screens.HomeScreen
import com.cursydev.masteryhub.screens.IntroScreen
import com.cursydev.masteryhub.screens.MediaScreen
import com.cursydev.masteryhub.screens.Screen
import com.cursydev.masteryhub.screens.SplashScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(generalViewModel: GeneralViewModel) {
    val generalUiState by generalViewModel.appState.collectAsState()
    val scope = rememberCoroutineScope()

    NavHost(navController =  generalUiState.navController!!, startDestination = Screen.SplashScreen.route) {
        composable(route = Screen.HomeScreen.route) {

            generalViewModel.setToolbarActions(ToolbarActionsData(
                onNavClick = {
                    scope.launch {
                        generalViewModel.drawerState.open()
                    }
                }
            ))
            generalViewModel.setToolbar(Toolbar.HomeScreenToolbar)
            HomeScreen(modifier = Modifier, generalViewModel.drawerState,  generalUiState.navController!!)
        }

        composable(route = Screen.MediaScreen.route) {
            MediaScreen(modifier = Modifier, generalViewModel.drawerState,  generalUiState.navController!!)
        }

        composable(route = Screen.SplashScreen.route) {
            SplashScreen(modifier = Modifier,  generalUiState.navController!!)
        }

        composable(route = Screen.IntroScreen.route) {
            IntroScreen(modifier = Modifier,  generalUiState.navController!!)
        }

        blogGraph(generalUiState.navController!!, generalViewModel)
    }
}



@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController) : T{
    val navGraphRoute = destination.parent?.route?: return viewModel()
    val parentEntry = remember(this){
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}