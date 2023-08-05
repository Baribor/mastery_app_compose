package com.cursydev.masteryhub

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cursydev.masteryhub.screens.HomeScreen
import com.cursydev.masteryhub.screens.IntroScreen
import com.cursydev.masteryhub.screens.MediaScreen
import com.cursydev.masteryhub.screens.Screen
import com.cursydev.masteryhub.screens.SplashScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(navController: NavHostController, drawerState: DrawerState) {

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(modifier = Modifier, drawerState)
        }

        composable(route = Screen.MediaScreen.route) {
            MediaScreen(modifier = Modifier, drawerState)
        }

        composable(route = Screen.SplashScreen.route) {
            SplashScreen(modifier = Modifier, navController)
        }

        composable(route = Screen.IntroScreen.route) {
            IntroScreen(modifier = Modifier, navController)
        }
    }
}