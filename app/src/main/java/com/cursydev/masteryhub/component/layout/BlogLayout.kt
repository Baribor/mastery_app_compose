package com.cursydev.masteryhub.component.layout

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cursydev.masteryhub.util.nav.BottomNav
import com.cursydev.masteryhub.util.nav.BottomNavLayout
import com.cursydev.masteryhub.util.nav.Toolbar
import com.cursydev.masteryhub.util.nav.ToolbarActionsData
import com.cursydev.masteryhub.util.nav.ToolbarLayout
import com.cursydev.masteryhub.component.ui.allViewModels
import com.cursydev.masteryhub.screens.Screen
import com.cursydev.masteryhub.screens.blog.BlogDetailScreen
import com.cursydev.masteryhub.screens.blog.BlogLikeScreen
import com.cursydev.masteryhub.screens.blog.MainBlogScreen
import com.cursydev.masteryhub.util.toBlogDetail


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogLayout(onExternalNav: (route: String)->Unit) {

    val controller = rememberNavController()

    LaunchedEffect(Unit){
        allViewModels.blogViewModel.startBlogFetch()
    }
    val navBackStackEntry = controller.currentBackStackEntryAsState()
    val destination = navBackStackEntry.value?.destination

    Scaffold(
        topBar = {
            if (destination?.hierarchy?.any { it.route == Screen.BlogDetailScreen.route } == true){
                return@Scaffold
            }
            Toolbar.BlogScreenToolbar.ToolbarLayout(actionsData = ToolbarActionsData(
                onNavClick = {
                    onExternalNav(Screen.HomeScreen.route)
                }
            ), allViewModels.blogViewModel.toolbarTitle.value)
        },

        bottomBar = {
            if (destination?.hierarchy?.any { it.route == Screen.BlogDetailScreen.route } == true){
                return@Scaffold
            }
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
                    MainBlogScreen{ bD ->
                        allViewModels.blogDetailViewModel.setCurrentBlogData(bD)
                        controller.navigate(Screen.BlogDetailScreen.route)
                    }
                }

                composable(route= Screen.BlogScreens.BlogLikeScreen.route){
                    BlogLikeScreen(controller)
                }

                composable(route = Screen.BlogDetailScreen.route){
                    BlogDetailScreen(blogData = allViewModels.blogDetailViewModel.currentBlogData){
                        controller.popBackStack()
                    }
                }
            }
        }
    }

}