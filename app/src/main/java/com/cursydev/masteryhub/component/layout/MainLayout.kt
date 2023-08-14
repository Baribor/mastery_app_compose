package com.cursydev.masteryhub.component.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cursydev.masteryhub.component.Drawer
import com.cursydev.masteryhub.component.ui.AppViewModelProvider
import com.cursydev.masteryhub.component.ui.GeneralViewModel
import com.cursydev.masteryhub.component.ui.allViewModels
import com.cursydev.masteryhub.screens.HomeScreen
import com.cursydev.masteryhub.screens.Screen
import com.cursydev.masteryhub.screens.blog.BlogDetailScreen
import com.cursydev.masteryhub.ui.theme.MasteryHubTheme
import kotlinx.coroutines.launch


@Composable
fun MainLayout(generalViewModel: GeneralViewModel) {

    val controller = rememberNavController()
    val scope = rememberCoroutineScope()
    val appState = generalViewModel.appState.collectAsState()

    allViewModels.blogViewModel = viewModel(factory = AppViewModelProvider.Factory)

    MasteryHubTheme(darkTheme = appState.value.isDarkTheme) {
        NavHost(navController = controller, route = "main", startDestination = Screen.HomeScreen.route){
            composable(route = Screen.HomeScreen.route){
                Drawer(generalViewModel){
                    HomeScreen(navController = controller, openDrawer = {
                        scope.launch {
                            generalViewModel.toggleDrawerState()
                        }
                    })
                }
            }

            composable(route= Screen.BlogScreen.route){
                BlogLayout {
                    controller.navigate(it){
                        popUpTo(Screen.BlogScreen.route){
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            }

            composable(route = Screen.MediaScreen.route){
                MediaLayout{
                    controller.navigate(it){
                        popUpTo(Screen.MediaScreen.route){
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            }
        }
    }


}