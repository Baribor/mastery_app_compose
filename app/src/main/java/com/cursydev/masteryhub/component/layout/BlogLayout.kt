package com.cursydev.masteryhub.component.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cursydev.masteryhub.component.nav.BottomNav
import com.cursydev.masteryhub.component.nav.BottomNavLayout
import com.cursydev.masteryhub.component.nav.Toolbar
import com.cursydev.masteryhub.component.nav.ToolbarActionsData
import com.cursydev.masteryhub.component.nav.ToolbarLayout
import com.cursydev.masteryhub.component.ui.allViewModels
import com.cursydev.masteryhub.screens.Screen
import com.cursydev.masteryhub.screens.blog.BlogLikeScreen
import com.cursydev.masteryhub.screens.blog.MainBlogScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogLayout(onExternalNav: (route: String)->Unit) {

    val controller = rememberNavController()

    LaunchedEffect(Unit){
        allViewModels.blogViewModel.startBlogFetch()
    }

    Scaffold(
        topBar = {

            Toolbar.BlogScreenToolbar.ToolbarLayout(actionsData = ToolbarActionsData(
                onNavClick = {
                    onExternalNav(Screen.HomeScreen.route)
                }
            ), allViewModels.blogViewModel.toolbarTitle.value)
        },

        bottomBar = {

            BottomNav.BlogScreenBottomNav.BottomNavLayout(controller, changeTitle = {
                allViewModels.blogViewModel.setTitle(it)
            }, onTabChange = {
                allViewModels.blogViewModel.setActiveTabRoute(it)
            })
        }
    ) {

        Surface(modifier = Modifier.padding(it)) {
            NavHost(navController = controller, startDestination = allViewModels.blogViewModel.activeTabRoute){

                composable(route= Screen.BlogScreens.MainBlogScreen.route){
                    MainBlogScreen(controller)
                }

                composable(route= Screen.BlogScreens.BlogLikeScreen.route){
                    BlogLikeScreen(controller)
                }

            }
        }
    }

}