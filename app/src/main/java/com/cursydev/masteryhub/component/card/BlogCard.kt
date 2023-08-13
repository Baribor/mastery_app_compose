package com.cursydev.masteryhub.component.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.cursydev.masteryhub.util.BlogData


@Composable
fun BlogCard(blogData: BlogData) {

    Card(modifier = Modifier.padding(8.dp).clickable {

    },
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )) {

        AsyncImage(model = blogData.imgUrl, contentDescription = blogData.mTitle,
            contentScale = ContentScale.FillBounds, modifier = Modifier
                .fillMaxWidth()
                .height(150.dp))

        Column(modifier = Modifier.fillMaxWidth().padding(8.dp, 0.dp)) {
            Text(text = blogData.mTitle, style = MaterialTheme.typography.headlineSmall, maxLines = 2, fontSize = 18.sp, lineHeight = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = blogData.desc, style = MaterialTheme.typography.bodySmall, maxLines = 4)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Outlined.ThumbUp, contentDescription = "Like blog", tint = MaterialTheme.colorScheme.onSurface)
                }
                Spacer(modifier = Modifier.width(12.dp))

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Outlined.Share, contentDescription = "Share blog", tint = MaterialTheme.colorScheme.onSurface)
                }

            }
        }
    }
}


@Preview
@Composable
fun BlogCardPrev() {

    val data = BlogData(
        mTitle = "Nigeria Federal Government earmarks N15bn for Safe Schools Initiative",
        desc = "The Federal Government has earmarked N15 billion in the 2023 budget for the implementation of the Safe Schools Initiative (SSI). The National Coordinator, Financing Safe Schools in Nigeria, Halima Illya Ibrahim stated this on Tuesday in Abuja while briefing journalists on the safe school initiative.",
        imgUrl = "https://masterymedia.com.ng/wp-content/uploads/2023/06/49f9af4e-school-pupils-in-class.jpg",
        fullUrl = "some full url"
    )

    MaterialTheme {
        BlogCard(blogData = data)
    }
}