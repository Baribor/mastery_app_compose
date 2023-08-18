package com.cursydev.masteryhub.screens.blog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cursydev.masteryhub.R
import org.jsoup.nodes.Element

@Composable
fun BlogBody(elems: List<LayoutType>) {

    LazyColumn{
        items(elems){
            when(it){
                is TextElem ->{
                    Text(text = it.text)
                }

                is ImageElem ->{
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        AsyncImage(model = with(ImageRequest.Builder(LocalContext.current)){
                            data(it.url)
                            error(R.drawable.broken_image)
                            fallback(R.drawable.broken_image)
                            build()
                        }, contentDescription = it.caption,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .width(370.dp)
                                .height(300.dp))
                        it.caption?.let {caption ->
                            Text(text = caption) }
                    }
                }
            }
        }
    }
}
