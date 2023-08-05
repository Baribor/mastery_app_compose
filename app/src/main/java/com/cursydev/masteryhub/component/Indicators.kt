package com.cursydev.masteryhub.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cursydev.masteryhub.ui.theme.MasteryGreen
import com.cursydev.masteryhub.ui.theme.MasteryHubTheme
import com.cursydev.masteryhub.ui.theme.Pink40
import com.cursydev.masteryhub.ui.theme.PurpleGrey40


@Composable
fun DotIndicators(modifier: Modifier = Modifier,
                  totalDots: Int,
                  selectedIndex: Int,
                  dotSize: Dp
) {
    val selectedColor = MasteryGreen
    val unSelectedColor =  Color.Transparent

    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        items(totalDots) { index ->
            IndicatorDot(
                color = if (index == selectedIndex) selectedColor else unSelectedColor,
                size = dotSize
            )

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}


@Preview
@Composable
fun DotIndicatorPreview() {
    MaterialTheme {
        DotIndicators(totalDots = 3, selectedIndex = 0, dotSize = 10.dp)
    }
}