package com.cursydev.masteryhub.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun IndicatorDot(modifier: Modifier = Modifier, size: Dp, color: Color) {
    Box(modifier = modifier
        .size(size)
        .clip(CircleShape)
        .background(color).border(width = 2.dp, color = MaterialTheme.colorScheme.primary, shape = CircleShape))
}
