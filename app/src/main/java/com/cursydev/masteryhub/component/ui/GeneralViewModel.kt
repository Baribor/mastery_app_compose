package com.cursydev.masteryhub.component.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GeneralViewModel: ViewModel() {

    private val _appState = MutableStateFlow(GeneralUiState())
    val appState : StateFlow<GeneralUiState> = _appState.asStateFlow()


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
}