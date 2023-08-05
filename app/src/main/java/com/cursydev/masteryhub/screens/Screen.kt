package com.cursydev.masteryhub.screens

sealed class Screen(val route: String){
    object IntroScreen: Screen("intro_screen")
    object HomeScreen: Screen("home_screen")
    object SplashScreen: Screen("splash_screen")
    object MediaScreen: Screen("media_screen")
    object BlogScreen: Screen("media_screen")
    object CommunityScreen: Screen("media_screen")

}
