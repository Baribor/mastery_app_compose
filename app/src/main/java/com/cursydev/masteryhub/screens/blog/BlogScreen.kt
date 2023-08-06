package com.cursydev.masteryhub.screens.blog

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.cursydev.masteryhub.component.layout.MainLayout
import com.cursydev.masteryhub.component.nav.BottomNav
import com.cursydev.masteryhub.component.nav.graph.blogGraph
import com.cursydev.masteryhub.component.ui.GeneralViewModel
import com.cursydev.masteryhub.ui.theme.MasteryHubTheme


@Composable
fun MainBlogScreen(navController: NavController) {

    Text(text = "Blogging!!!!!!!!!!")
}


@Preview
@Composable
fun BlogScreenPreview() {
    val navCont = rememberNavController()
    val generalViewModel: GeneralViewModel = viewModel()

    generalViewModel.setBottomNav(BottomNav.BlogScreenBottomNav)

    MasteryHubTheme(darkTheme = false) {
        MainLayout(generalViewModel) {
            NavHost(navController = navCont, startDestination = "blogs") {
                blogGraph(navController = navCont, generalViewModel)
            }
        }

    }
}