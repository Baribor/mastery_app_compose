package com.cursydev.masteryhub.component.ui

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.cursydev.masteryhub.component.nav.BottomNav
import com.cursydev.masteryhub.component.nav.Toolbar
import com.cursydev.masteryhub.component.nav.ToolbarActionsData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


@OptIn(ExperimentalMaterial3Api::class)
class GeneralViewModel: ViewModel() {

    private val _appState = MutableStateFlow(GeneralUiState())
    val appState : StateFlow<GeneralUiState> = _appState.asStateFlow()

    var drawerState by mutableStateOf(DrawerState(DrawerValue.Closed))

    var toolbarActionsData by mutableStateOf<ToolbarActionsData?>(null)
        private set
    
    var currentBlogTab by mutableStateOf(0)
        private set

    var bottomNavActions by mutableStateOf<List<(()->Unit)>?>(null)
        private set

    init {
        _appState.value = GeneralUiState(isDarkTheme = false)
    }


    fun toggleTheme(){
        _appState.update { currentState ->
            currentState.copy(
                isDarkTheme = !appState.value.isDarkTheme
            )
        }
    }

    fun setNavController(navController: NavHostController){
        _appState.update {generalUiState ->
            generalUiState.copy(
                navController = navController
            )
        }
    }

    fun setToolbar(toolbar: Toolbar?){
        _appState.update { generalUiState ->
            generalUiState.copy(
                toolbar = toolbar
            )
        }
    }

    fun setToolbarActions(tAD: ToolbarActionsData?){
        toolbarActionsData = tAD
    }

    fun setBottomNav(bottomNav: BottomNav?){
        _appState.update { generalUiState ->
            generalUiState.copy(
                bottomNav = bottomNav
            )
        }
    }

    fun setBottomNavigationActions(actions: List<()->Unit>){
        bottomNavActions = actions
    }

    fun setBlogCurrentTab(index:Int){
        currentBlogTab = index
    }
}