package com.cursydev.masteryhub.component.ui

import androidx.navigation.NavHostController
import com.cursydev.masteryhub.component.nav.BottomNav
import com.cursydev.masteryhub.component.nav.Toolbar

data class GeneralUiState(
    val isDarkTheme: Boolean = false,
    val toolbar: Toolbar? = null,
    val bottomNav: BottomNav? = null,
    val navController: NavHostController? = null
)
