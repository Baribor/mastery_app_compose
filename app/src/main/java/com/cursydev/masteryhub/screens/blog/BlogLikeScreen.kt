package com.cursydev.masteryhub.screens.blog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.cursydev.masteryhub.component.ui.BlogViewModel


@Composable
fun BlogLikeScreen(navController: NavController?) {

    Surface(color = MaterialTheme.colorScheme.background) {
        LazyColumn{
            items(100){
                Text(text = "This too will pass....$it")
            }
        }
    }

}