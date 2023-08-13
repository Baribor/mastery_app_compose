package com.cursydev.masteryhub.component.layout

import androidx.compose.ui.graphics.vector.ImageVector

interface Navigator {
    val route: String
    val title: String
    val icon: ImageVector
}