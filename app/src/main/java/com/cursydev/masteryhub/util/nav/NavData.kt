package com.cursydev.masteryhub.util.nav

import androidx.compose.ui.graphics.vector.ImageVector

data class ToolbarUiData(
    val title:String,
    val navIcon: ImageVector? = null,
    val actions: List<ImageVector>? = null
)


data class ToolbarActionsData(
    val onNavClick : (()->Unit)? = null,
    val actionHandlers : List<()->Unit>? = null
)

