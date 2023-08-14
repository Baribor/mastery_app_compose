package com.cursydev.masteryhub.screens.blog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cursydev.masteryhub.component.card.BlogCard
import com.cursydev.masteryhub.component.ui.allViewModels
import com.cursydev.masteryhub.component.ui.shimmerEffect
import com.cursydev.masteryhub.screens.Screen
import com.cursydev.masteryhub.ui.theme.MasteryHubTheme
import com.cursydev.masteryhub.util.BlogData
import com.cursydev.masteryhub.util.toBlogDetail


@Composable
fun MainBlogScreen(onShowDetail: (blogData: BlogData)->Unit) {

    if(allViewModels.blogViewModel.blogs.value.isNotEmpty()){
        LazyColumn(modifier = Modifier.background(MaterialTheme.colorScheme.background),contentPadding = PaddingValues(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)){
            items(allViewModels.blogViewModel.blogs.value.size){
                val blogData = allViewModels.blogViewModel.blogs.value[it]
                BlogCard(blogData){ bD ->
                    onShowDetail(bD)
                }
            }
        }
    }else{
        LazyColumn(contentPadding = PaddingValues(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)){
            items(6){
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)) {
                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .width(100.dp)
                        .shimmerEffect())
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Box(modifier = Modifier
                            .height(32.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(topEnd = 32.dp, bottomEnd = 32.dp))
                            .shimmerEffect())
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(modifier = Modifier
                            .height(32.dp)
                            .fillMaxWidth(.7f)
                            .clip(RoundedCornerShape(topEnd = 32.dp, bottomEnd = 32.dp))
                            .shimmerEffect())
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun BlogScreenPreview() {

    MasteryHubTheme(darkTheme = false) {

    }
}


fun NavController.printGraph(){
    println("--------------------")
    currentBackStack.value.forEach {
        println("screen : ${it.destination.route}")
    }
    println("--------------------")
}