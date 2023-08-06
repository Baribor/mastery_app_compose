package com.cursydev.masteryhub.component.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

sealed class Toolbar(val uiData: ToolbarUiData) {
    object HomeScreenToolbar :
        Toolbar(ToolbarUiData("Home", Icons.Filled.Menu, listOf(Icons.Filled.Search)))

    object BlogScreenToolbar : Toolbar(
        ToolbarUiData(
            "Blogs", Icons.Filled.ArrowBack
        )
    )

    object BlogLikeScreenToolbar : Toolbar(
        ToolbarUiData(
            "Liked Blogs", Icons.Filled.ArrowBack
        )
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar.ToolbarLayout(actionsData: ToolbarActionsData?) {
    TopAppBar(
        title = {
            Text(text = uiData.title, style = MaterialTheme.typography.titleMedium)

        },
        navigationIcon = {
            uiData.navIcon?.let {
                IconButton(onClick = { actionsData?.onNavClick?.let { onNavClick -> onNavClick() } }) {
                    Icon(imageVector = it, null)
                }
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        actions = {
            uiData.actions?.let { icons ->
                icons.forEachIndexed { index, imageVector ->
                    IconButton(onClick = {
                        actionsData?.actionHandlers?.let { actionsHandlers ->
                            actionsHandlers[0]()
                        }
                    }) {
                        Icon(imageVector = imageVector, null)
                    }
                }
            }
        }
    )
}


