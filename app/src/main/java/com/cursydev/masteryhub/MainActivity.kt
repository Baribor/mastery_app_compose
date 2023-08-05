package com.cursydev.masteryhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.cursydev.masteryhub.component.Drawer
import com.cursydev.masteryhub.ui.theme.MasteryHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RootCompose()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootCompose() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    MasteryHubTheme {
        Drawer(drawerState = drawerState, navController){
            Navigation(navController = navController, drawerState)
        }
    }
}
