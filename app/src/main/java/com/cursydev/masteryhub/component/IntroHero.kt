package com.cursydev.masteryhub.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.cursydev.masteryhub.ui.theme.MasteryGreen


val constraintSet = ConstraintSet{

    val image = createRefFor("image")
    val text = createRefFor("text")

    val imageBaseLine = createGuidelineFromTop(0.65f)

    constrain(image){
        top.linkTo(parent.top)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        bottom.linkTo(imageBaseLine)
    }

    constrain(text){
        top.linkTo(image.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }
}


@Composable
fun IntroHeader(imgId: Int, text: String, modifier: Modifier = Modifier) {

        ConstraintLayout(constraintSet = constraintSet, modifier = Modifier.fillMaxSize()) {
            Image(painter = painterResource(id = imgId), contentDescription = text, contentScale = ContentScale.FillWidth, modifier = Modifier.fillMaxWidth().layoutId("image"))
            Text(text = text, style = MaterialTheme.typography.titleMedium, color = MasteryGreen, modifier= Modifier.layoutId("text"), textAlign = TextAlign.Center)
        }


}