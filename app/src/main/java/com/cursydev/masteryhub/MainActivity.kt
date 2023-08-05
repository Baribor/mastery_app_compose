package com.cursydev.masteryhub

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.cursydev.masteryhub.component.Drawer
import com.cursydev.masteryhub.component.ui.GeneralViewModel
import com.cursydev.masteryhub.ui.theme.MasteryHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RootCompose {
                finish()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootCompose(exitApp: ()->Unit) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val generalViewModel: GeneralViewModel = viewModel()
    val appUiState by generalViewModel.appState.collectAsState()
    /* Back handler for handling back press events */
    BackHandler{
        if(!navController.popBackStack()){
            exitApp()
        }
    }

    MasteryHubTheme(darkTheme = appUiState.isDarkTheme) {
        Drawer(drawerState = drawerState, navController, isLightTheme = appUiState.isDarkTheme, onThemeToggle = {generalViewModel.toggleTheme()}){
            Navigation(navController = navController, drawerState)
        }
    }
}
