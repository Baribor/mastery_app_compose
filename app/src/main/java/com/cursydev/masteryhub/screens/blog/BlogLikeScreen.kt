package com.cursydev.masteryhub.screens.blog

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.cursydev.masteryhub.component.ui.BlogViewModel


@Composable
fun BlogLikeScreen(navController: NavController?) {

    Column {
        Text(text = "Main blog here")
        Button(onClick = {
            navController?.printGraph()
        }) {
            Text(text = "I like this blog!!!!!!")
        }
    }

}