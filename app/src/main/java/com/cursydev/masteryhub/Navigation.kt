package com.cursydev.masteryhub

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cursydev.masteryhub.component.layout.MainLayout
import com.cursydev.masteryhub.component.ui.GeneralViewModel
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
            IntroScreen(navController = controller)
        }

        composable(route = Screen.HomeScreen.route){
            MainLayout(allViewModels.generalViewModel)
        }
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
