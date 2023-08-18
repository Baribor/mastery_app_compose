package com.cursydev.masteryhub.component.ui

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


@OptIn(ExperimentalMaterial3Api::class)
class GeneralViewModel: ViewModel() {

    private val _appState = MutableStateFlow(GeneralUiState())
    val appState : StateFlow<GeneralUiState> = _appState.asStateFlow()

    lateinit var navController: NavController

    var drawerState by mutableStateOf(DrawerState(DrawerValue.Closed))
        private set

    var exitApp: (()->Unit)? = null
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

    suspend fun toggleDrawerState(){
        when(drawerState.isOpen){
            true -> drawerState.close()
            else -> drawerState.open()
        }
    }

    fun setOnExit(onExit: ()->Unit){
        exitApp = onExit
    }

}



class ViewModels {
    lateinit var blogViewModel: BlogViewModel
    lateinit var generalViewModel: GeneralViewModel
    lateinit var blogDetailViewModel: BlogDetailViewModel
}

val allViewModels = ViewModels()