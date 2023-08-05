package com.cursydev.masteryhub.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cursydev.masteryhub.component.layout.ToolbarData
import com.cursydev.masteryhub.component.layout.ToolbarLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, drawerState: DrawerState? = null) {
    val scope = rememberCoroutineScope()

    ToolbarLayout(toolbarData = ToolbarData("Home", navigationIcon = {
        IconButton(onClick = {
            scope.launch { drawerState?.open() }
        }) {
            Icon(imageVector = Icons.Rounded.Menu, contentDescription = "Drawer")
        }
    },
    actions = {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search")
        }

        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Rounded.Refresh, contentDescription = "Refresh")
        }
    })) {

        /*Main content*/
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Home Screen", modifier = Modifier.align(Alignment.Center))
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen()
    }
}