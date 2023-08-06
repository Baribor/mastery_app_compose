package com.cursydev.masteryhub.component.nav.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cursydev.masteryhub.component.nav.BottomNav
import com.cursydev.masteryhub.component.nav.Toolbar
import com.cursydev.masteryhub.component.nav.ToolbarActionsData
import com.cursydev.masteryhub.component.ui.GeneralViewModel
import com.cursydev.masteryhub.screens.BlogScreen
import com.cursydev.masteryhub.screens.Screen
import com.cursydev.masteryhub.screens.blog.BlogLikeScreen
import com.cursydev.masteryhub.screens.blog.MainBlogScreen

fun NavGraphBuilder.blogGraph(
    navController: NavController,
    generalViewModel: GeneralViewModel
) {
    val tabs = listOf(BlogScreen.MainBlogScreen.route, BlogScreen.BlogLikeScreen.route)
    val bottomNavActions = listOf(
        {
            navController.navigate("blogs"){
                launchSingleTop = true
            }
        },
        {
            navController.navigate("blogs"){
                launchSingleTop = true
            }
        }
    )

    navigation(
        startDestination = tabs[generalViewModel.currentBlogTab],
        route = "blogs"
    ) {

        composable(BlogScreen.MainBlogScreen.route) {
            generalViewModel.apply {
                setToolbar(Toolbar.BlogScreenToolbar)
                setToolbarActions(ToolbarActionsData(
                    onNavClick = {
                        navController.navigate(Screen.HomeScreen.route){
                            popUpTo("blogs"){
                                inclusive = true
                            }
                        }
                        setBottomNav(null)
                    }
                ))

                setBottomNav(BottomNav.BlogScreenBottomNav)
                setBottomNavigationActions(bottomNavActions)
            }
            MainBlogScreen(navController)
        }

        composable(BlogScreen.BlogLikeScreen.route) {
            generalViewModel.apply {
                setToolbar(Toolbar.BlogLikeScreenToolbar)
                setToolbarActions(null)
                setBottomNav(BottomNav.BlogScreenBottomNav)
                setBottomNavigationActions(bottomNavActions)
            }

            BlogLikeScreen()
        }

    }
}