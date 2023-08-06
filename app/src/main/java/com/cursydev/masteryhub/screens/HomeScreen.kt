package com.cursydev.masteryhub.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cursydev.masteryhub.R
import com.cursydev.masteryhub.ui.theme.MasteryHubTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    drawerState: DrawerState? = null,
    navController: NavController? = null
) {

    /*Main content*/
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(8.dp)) {
            items(2) {
                val titles = listOf("FOUNDATION", "MEDIA")
                val imgs = listOf(R.drawable.masteryf1, R.drawable.playlist)

                Card(modifier = Modifier.padding(6.dp, 12.dp),
                    shape = RoundedCornerShape(2.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    onClick = {
                        if (it == 1) {
                            navController?.navigate(Screen.MediaScreen.route)
                        }
                    }) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = imgs[it]),
                            contentDescription = "Mastery foundation",
                            modifier = Modifier
                                .height(dimensionResource(id = R.dimen.media_card_height))
                                .fillMaxWidth(),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
                            text = titles[it],
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(4.dp)
                        )
                    }

                }
            }

            item(span = { GridItemSpan(2) }) {
                Card(modifier = Modifier.padding(4.dp), colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ), onClick = {
                    navController?.navigate("blogs") {
                        launchSingleTop = true
                        restoreState = true
                    }
                }) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.post),
                            contentDescription = "Mastery foundation",
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxWidth(),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
                            text = "BLOG POSTS",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(4.dp)
                        )
                    }

                }
            }
        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreenPreview() {
    MasteryHubTheme(false) {
        HomeScreen()
    }
}