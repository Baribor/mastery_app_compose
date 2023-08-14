package com.cursydev.masteryhub.screens.blog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cursydev.masteryhub.R
import com.cursydev.masteryhub.ui.theme.DrawerItemSelectedLight
import com.cursydev.masteryhub.ui.theme.MasteryHubTheme
import com.cursydev.masteryhub.util.BlogData
import com.cursydev.masteryhub.util.BlogDetail

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogDetailScreen(blogDetail: BlogDetail, onNavBack: ()->Unit = {}) {

    Scaffold(

        topBar = {
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)) {
                Text(text = blogDetail.mTitle, maxLines = 3, style = MaterialTheme.typography.headlineSmall, fontSize = 18.sp, fontWeight = FontWeight.Bold, lineHeight = 24.sp, color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier
                    .clip(
                        RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                    )
                    .background(color = MaterialTheme.colorScheme.primary)
                    .padding(8.dp))
                
                Row(modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "Author pic", tint = MaterialTheme.colorScheme.onPrimary, modifier = Modifier
                        .height(
                            dimensionResource(id = R.dimen.blog_author_image_size)
                        )
                        .width(dimensionResource(id = R.dimen.blog_author_image_size))
                        .clip(
                            CircleShape
                        ))
                    Text(text = blogDetail.author,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(DrawerItemSelectedLight)
                        .padding(vertical = 2.dp, horizontal = 6.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary)

                    Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.End) {
                        Text(
                            text = "02/08/2023",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .clip(RoundedCornerShape(bottomStart = 16.dp, topStart = 16.dp))
                                .background(MaterialTheme.colorScheme.primary)
                                .padding(vertical = 2.dp, horizontal = 6.dp)
                        )
                    }

                }
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = { onNavBack() },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back arrow")
            }
        }
    ) {

        Surface(modifier = Modifier.padding(it)) {

            LazyColumn(contentPadding = PaddingValues(4.dp)){
                items(10){
                    Text(text = "The Federal Government has earmarked N15 billion in the 2023 budget for the implementation of the Safe Schools Initiative (SSI). The National Coordinator, Financing Safe Schools in Nigeria, Halima Illya Ibrahim stated this on Tuesday in Abuja while briefing journalists on the safe school initiative.")
                }
            }
        }
    }
}


@Preview
@Composable
fun BlogDetailScreenPreview() {
    val data = BlogDetail("A forensic audit of Central Bank of Nigerian underway - Nigeria President Tinubu", "Bassey Nton Nton","", "")
    MasteryHubTheme(darkTheme = false) {
        BlogDetailScreen(blogDetail = data)
    }
}