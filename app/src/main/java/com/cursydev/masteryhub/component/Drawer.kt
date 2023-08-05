package com.cursydev.masteryhub.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cursydev.masteryhub.R
import com.cursydev.masteryhub.screens.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Drawer(drawerState: DrawerState, navHostController: NavHostController? = null, mainContent: @Composable () -> Unit = {}) {
    val scope = rememberCoroutineScope()
    val navs = listOf("Home", "Media", "Blog", "Community")
    val routes = listOf(Screen.HomeScreen.route, Screen.MediaScreen.route, Screen.BlogScreen.route, Screen.CommunityScreen.route)

    val icons = listOf(
        Icons.Filled.Home,
        Icons.Filled.PlayCircle,
        Icons.Filled.Newspaper,
        Icons.Filled.People
    )
    val nonSelectableNav = listOf("Settings", "Contact us", "About us", "Our Policy", "Share App")
    val nonSelectableIcons = listOf(
        Icons.Filled.Settings,
        Icons.Filled.Chat,
        Icons.Filled.Info,
        Icons.Filled.Policy,
        Icons.Filled.Share
    )

    val selectedItem = remember {
        mutableStateOf(navs[0])
    }

    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        ModalDrawerSheet(drawerShape = RectangleShape, modifier = Modifier.fillMaxWidth(.8f)) {

            Surface {
                Row(
                    horizontalArrangement = Arrangement.Center, modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(bottomEnd = 30.dp, bottomStart = 30.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.mastery_logo),
                        contentDescription = "logo",
                        modifier = Modifier.size(120.dp)
                    )
                }
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    ThemeToggler()
                }

            }

            Spacer(modifier = Modifier.height(10.dp))

            navs.forEachIndexed { i, name ->
                NavigationDrawerItem(
                    label = { Text(text = name) },
                    selected = selectedItem.value == name,
                    onClick = {
                        scope.launch { drawerState.close() }
                        selectedItem.value = name
                        navHostController?.navigate(routes[i])
                    },
                    icon = { Icon(icons[i], contentDescription = null) },
                    shape = RoundedCornerShape(topEnd = 30.dp, bottomEnd = 30.dp),
                    modifier = Modifier.padding(end = 20.dp)
                )
            }

            Divider()
            Spacer(modifier = Modifier.height(24.dp))

            nonSelectableNav.forEachIndexed { i, name ->
                NavigationDrawerItem(
                    label = { Text(text = name) },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                    },
                    icon = { Icon(nonSelectableIcons[i], contentDescription = null) },
                    shape = RoundedCornerShape(topEnd = 30.dp, bottomEnd = 30.dp),
                    modifier = Modifier.padding(end = 20.dp)
                )
            }

        }
    }) {
        mainContent()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DrawerPreview() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
    MaterialTheme {
        Drawer(drawerState)
    }
}