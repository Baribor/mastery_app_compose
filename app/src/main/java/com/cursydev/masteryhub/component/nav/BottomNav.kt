package com.cursydev.masteryhub.component.nav

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.cursydev.masteryhub.component.ui.GeneralViewModel


sealed class BottomNav(val bottomNavUiData: BottomNavUiData) {

    object BlogScreenBottomNav : BottomNav(
        BottomNavUiData(
            listOf("Blog", "Liked"),
            listOf(Icons.Filled.Newspaper, Icons.Filled.ThumbUp)
        )
    )
}


@Composable
fun BottomNav.BottomNavLayout(generalViewModel: GeneralViewModel) {

    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.surface
    ) {
        bottomNavUiData.icons.forEachIndexed { index, imageVector ->

            BottomNavigationItem(selected = generalViewModel.currentBlogTab == index, onClick = {

                generalViewModel.bottomNavActions?.let {
                    generalViewModel.setBlogCurrentTab(index)
                    it[index]()
                }

            }, icon = {
                Icon(imageVector = imageVector, contentDescription = null)
            },
                selectedContentColor = MaterialTheme.colorScheme.tertiary,
                unselectedContentColor = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}