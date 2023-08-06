package com.cursydev.masteryhub.screens

import android.media.MediaActionSound
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import com.cursydev.masteryhub.component.layout.Navigator

sealed class Screen(override val route: String): Navigator{
    object IntroScreen: Screen("intro_screen")
    object HomeScreen: Screen("home_screen")
    object SplashScreen: Screen("splash_screen")
    object MediaScreen: Screen("media_screen")
}


sealed class BlogScreen(override val route: String, val icon: ImageVector, val title:String):Navigator{

    object MainBlogScreen: BlogScreen("blog", Icons.Filled.Newspaper, "Blog")
    object BlogLikeScreen: BlogScreen("liked_blog", Icons.Filled.ThumbUp, "Liked")
}


sealed class MediaScreen(override val route: String): Navigator{

    object VideoScreen: MediaScreen("videos")
    object MusicScreen: MediaScreen("musics")
}
