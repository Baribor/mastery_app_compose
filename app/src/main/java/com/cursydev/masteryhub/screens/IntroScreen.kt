package com.cursydev.masteryhub.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavHostController
import com.cursydev.masteryhub.R
import com.cursydev.masteryhub.component.DotIndicators
import com.cursydev.masteryhub.component.IntroHeader
import com.cursydev.masteryhub.ui.theme.MasteryGreen
import com.cursydev.masteryhub.ui.theme.MasteryHubTheme

val constraintSet = ConstraintSet {
    val hero = createRefFor("hero")
    val footer = createRefFor("footer")

    constrain(hero) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        bottom.linkTo(footer.top)
    }

    constrain(footer) {
        bottom.linkTo(parent.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }
}


@Composable
fun IntroScreen(modifier: Modifier = Modifier, navController: NavHostController? = null) {

    val imgDescs = stringArrayResource(id = R.array.intro_pics_desc)
    val imgIds =
        intArrayOf(R.drawable.news_update, R.drawable.entertainment, R.drawable.motivational)
    var index by remember {
        mutableStateOf(0)
    }

    ConstraintLayout(
        constraintSet = constraintSet, modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 8.dp)
    ) {

        /*Hero section*/
        Crossfade(targetState = index){ index->
            IntroHeader(
                imgId = imgIds[index],
                text = imgDescs[index],
                modifier = Modifier.layoutId("hero")
            )
        }
        Column(
            modifier = Modifier.layoutId("footer"),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            /*Indicator*/
            DotIndicators(totalDots = 3, selectedIndex = index, dotSize = 12.dp)
            Spacer(modifier = Modifier.height(32.dp))

            /*Navigation*/
            Row(
                modifier = Modifier
                    .fillMaxWidth(), horizontalArrangement = Arrangement.End
            ) {
                AnimatedVisibility(visible = index < 2) {

                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "SKIP", color = MasteryGreen)
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Button(
                    onClick = {
                        if (index == 2)
                        {
                            navController?.apply {
                                popBackStack()
                                navigate(Screen.HomeScreen.route)
                            }
                            return@Button
                        }
                        index++
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MasteryGreen),
                    modifier = Modifier.animateContentSize()
                ) {
                    Text(text = if(index<2) "Next" else "Continue")
                }
            }
        }

    }
}


@Preview
@Composable
fun IntroScreenPreview() {
    MasteryHubTheme {
        IntroScreen()
    }
}