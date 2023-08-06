package com.cursydev.masteryhub.component.layout

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cursydev.masteryhub.component.nav.BottomNavLayout
import com.cursydev.masteryhub.component.nav.ToolbarLayout
import com.cursydev.masteryhub.component.ui.GeneralUiState
import com.cursydev.masteryhub.component.ui.GeneralViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(generalViewModel: GeneralViewModel,content: @Composable ()->Unit) {
    val generalUiState by generalViewModel.appState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        // Toolbar
        topBar = {
            generalUiState.toolbar?.ToolbarLayout(generalViewModel.toolbarActionsData)
        },

        // Bottom Nav
        bottomBar = {
            generalUiState.bottomNav?.BottomNavLayout(generalViewModel)
        },


        ) { paddingValues ->

        /* Content */
        Surface(modifier = Modifier.padding(paddingValues)) {
            content()
        }
    }
}