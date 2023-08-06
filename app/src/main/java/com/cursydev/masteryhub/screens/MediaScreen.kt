package com.cursydev.masteryhub.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaScreen(
    modifier: Modifier = Modifier,
    drawerState: DrawerState? = null,
    navHostController: NavController? = null
) {

    Box(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Media Screen", modifier = Modifier.align(Alignment.Center))
    }

}