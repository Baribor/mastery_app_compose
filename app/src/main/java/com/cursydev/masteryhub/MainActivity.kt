package com.cursydev.masteryhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cursydev.masteryhub.component.ui.GeneralViewModel
import com.cursydev.masteryhub.ui.theme.MasteryHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RootCompose {
                finish()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootCompose(exitApp: () -> Unit) {
    NavigationRoutes {
        exitApp()
    }
}
