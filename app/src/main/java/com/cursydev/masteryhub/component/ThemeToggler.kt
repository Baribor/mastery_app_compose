package com.cursydev.masteryhub.component

import androidx.compose.animation.Crossfade
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cursydev.masteryhub.component.ui.GeneralViewModel


@Composable
fun ThemeToggler(isDarkTheme:Boolean, onThemeToggle: ()->Unit) {

    IconButton(onClick = { onThemeToggle() }) {
        Crossfade(targetState = isDarkTheme) { isDarkMode ->
            Icon(
                if (isDarkMode) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                contentDescription = "Theme changer"
            )
        }

    }
}