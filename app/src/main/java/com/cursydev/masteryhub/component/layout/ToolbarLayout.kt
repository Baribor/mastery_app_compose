package com.cursydev.masteryhub.component.layout

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.cursydev.masteryhub.ui.theme.MasteryGreen


data class ToolbarData(
    val title: String = "",
    val navigationIcon: @Composable () -> Unit = {},
    val actions: @Composable () -> Unit = {}
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarLayout(toolbarData: ToolbarData, content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = toolbarData.title, style = MaterialTheme.typography.titleMedium)

                },
                navigationIcon = {
                    toolbarData.navigationIcon()
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                actions = {
                    toolbarData.actions()
                }
            )
        }
    )

    { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            content()
        }
    }
}