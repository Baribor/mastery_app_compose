package com.cursydev.masteryhub

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.preference.PreferenceManager
import com.cursydev.masteryhub.component.layout.MainLayout
import com.cursydev.masteryhub.component.ui.allViewModels
import com.cursydev.masteryhub.screens.IntroScreen
import com.cursydev.masteryhub.screens.Screen
import com.cursydev.masteryhub.screens.SplashScreen


@Composable
fun NavigationRoutes(onExit: ()->Unit) {

    val controller = rememberNavController()
    allViewModels.generalViewModel = viewModel()

    allViewModels.generalViewModel.setOnExit {
        onExit()
    }

    NavHost(navController = controller, startDestination = Screen.SplashScreen.route){

        composable(route = Screen.SplashScreen.route){
            SplashScreen(navController = controller)
        }

        composable(route = Screen.IntroScreen.route){
            IntroScreen{
                with(PreferenceManager.getDefaultSharedPreferences(MasteryApp.getApp()).edit()){
                    putBoolean(it, true)
                    apply()
                }
                controller.apply {
                    popBackStack()
                    navigate(Screen.HomeScreen.route)
                }
            }
        }

        composable(route = Screen.HomeScreen.route){
            MainLayout(allViewModels.generalViewModel)
        }
    }
}
