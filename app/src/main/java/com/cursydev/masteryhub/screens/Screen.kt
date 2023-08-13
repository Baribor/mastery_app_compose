package com.cursydev.masteryhub.screens

import android.media.MediaActionSound
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.VideoFile
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import com.cursydev.masteryhub.component.layout.Navigator

const val BASE = "base_route"
const val BLOG_BASED = "blog_base"

sealed class Screen(val route: String){
    object IntroScreen: Screen("intro_screen")
    object HomeScreen: Screen("home_screen")
    object SplashScreen: Screen("splash_screen")
    object MediaScreen: Screen("media_screen")
    object BlogScreen: Screen("blog_screen")

    sealed class BlogScreens(override val route: String, override val icon: ImageVector, override val title:String): Navigator{
        object MainBlogScreen: BlogScreens("blog", Icons.Filled.Newspaper, "Blog")
        object BlogLikeScreen: BlogScreens("liked_blog", Icons.Filled.ThumbUp, "Liked")
    }


    sealed class MediaScreens(override val route: String, override val icon: ImageVector, override val title:String): Navigator{

        object VideoScreen: MediaScreens("videos", Icons.Filled.VideoFile, "Videos")
        object MusicScreen: MediaScreens("musics", Icons.Filled.LibraryMusic, "Music")
    }
}

