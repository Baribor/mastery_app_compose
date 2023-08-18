package com.cursydev.masteryhub.util.nav

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cursydev.masteryhub.component.layout.Navigator
import com.cursydev.masteryhub.component.ui.allViewModels
import com.cursydev.masteryhub.screens.Screen


sealed class BottomNav(val bottomNavScreens: List<Navigator>) {

    object BlogScreenBottomNav : BottomNav(
        listOf(
            Screen.BlogScreens.MainBlogScreen,
            Screen.BlogScreens.BlogLikeScreen
        )
    )

    object MediaScreenBottomNav : BottomNav(
        listOf(
           Screen.MediaScreens.VideoScreen,
           Screen.MediaScreens.MusicScreen,
        )
    )


}


@Composable
fun BottomNav.BottomNavLayout(navController: NavController?, changeTitle: (title:String)->Unit={}, onTabChange: (route: String)->Unit = {}) {

    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.surface
    ) {
        val navBackStackEntry = navController?.currentBackStackEntryAsState()
        val destination = navBackStackEntry?.value?.destination

        bottomNavScreens.forEach { screen ->
            BottomNavigationItem(selected =  destination?.hierarchy?.any { it.route == screen.route } == true, onClick = {

                navController?.navigate(screen.route){
                    popUpTo(allViewModels.blogViewModel.activeTabRoute) {
                        saveState = true
                        inclusive = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                changeTitle(screen.title)
                //onTabChange(screen.route)
            },
                label = {
                     Text(text = screen.title, style = MaterialTheme.typography.titleSmall)
                },

                icon = {
                Icon(imageVector = screen.icon, contentDescription = screen.title)
            },
                selectedContentColor = MaterialTheme.colorScheme.tertiary,
                unselectedContentColor = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}