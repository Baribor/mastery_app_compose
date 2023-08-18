package com.cursydev.masteryhub.screens

import android.widget.ImageView
import android.widget.LinearLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.preference.PreferenceManager
import com.cursydev.masteryhub.MasteryApp
import com.cursydev.masteryhub.R
import com.cursydev.masteryhub.ui.theme.MasteryHubTheme
import kotlinx.coroutines.delay


@Composable
fun SplashBackground() {
    AndroidView(modifier = Modifier.fillMaxSize(),
        factory = { context ->
            ImageView(context).apply {
                layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
                setBackgroundResource(R.drawable.splash_background)
            }
    })
}


@Composable
fun SplashScreen(modifier: Modifier = Modifier, navController: NavController? = null) {
    val introLaunched = PreferenceManager.getDefaultSharedPreferences(MasteryApp.getApp()).getBoolean(
        stringResource(id = R.string.intro_launched), false)

    LaunchedEffect(key1 = null){
        delay(3000)

        navController?.apply {
            popBackStack()
            navigate(if(introLaunched) Screen.HomeScreen.route  else Screen.IntroScreen.route)
        }
    }

    Box(modifier = Modifier.fillMaxSize()){
        SplashBackground()
        Row(modifier = Modifier
            .fillMaxSize(), horizontalArrangement = Arrangement.Center) {
        Column(modifier= Modifier.align(Alignment.CenterVertically)) {
            Image(painter = painterResource(id = R.drawable.mastery_logo), contentDescription = stringResource(
                id = R.string.mastery_logo_desc
            ), modifier= Modifier
                .align(Alignment.CenterHorizontally)
                .size(100.dp))
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = stringResource(id =R.string.mastery_media_nigeria), modifier=Modifier.align(Alignment.CenterHorizontally), style = TextStyle(color = colorResource(
                id = R.color.mastery_green
            ), fontSize = 20.sp))
        }
    }
    }


}


@Preview(
    showSystemUi = false
)
@Composable
fun SplashScreenPreview() {
    MasteryHubTheme {
        SplashScreen()
    }
}