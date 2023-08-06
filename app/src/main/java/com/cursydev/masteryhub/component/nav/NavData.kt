package com.cursydev.masteryhub.component.nav

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


data class BottomNavUiData(
    val titles: List<String>,
    val icons: List<ImageVector>
)

