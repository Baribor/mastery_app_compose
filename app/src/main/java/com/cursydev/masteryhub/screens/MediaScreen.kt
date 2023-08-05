package com.cursydev.masteryhub.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cursydev.masteryhub.component.layout.ToolbarData
import com.cursydev.masteryhub.component.layout.ToolbarLayout
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaScreen(modifier: Modifier = Modifier, drawerState: DrawerState? = null) {
    val scope = rememberCoroutineScope()

    ToolbarLayout(toolbarData = ToolbarData("Media", navigationIcon = {
        IconButton(onClick = {
            scope.launch { drawerState?.open() }
        }) {
            Icon(imageVector = Icons.Rounded.Menu, contentDescription = "Drawer")
        }
    })) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Media Screen", modifier = Modifier.align(Alignment.Center))
        }
    }


}