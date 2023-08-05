package com.cursydev.masteryhub.component

import androidx.compose.animation.Crossfade
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


@Composable
fun ThemeToggler() {
    val isLightMode = remember {
        mutableStateOf(true)
    }

    IconButton(onClick = { isLightMode.value = !isLightMode.value }) {
        Crossfade(targetState = isLightMode) { isLightMode ->
            Icon(
                if (isLightMode.value) Icons.Filled.DarkMode else Icons.Filled.LightMode,
                contentDescription = "Darkmode"
            )
        }

    }
}