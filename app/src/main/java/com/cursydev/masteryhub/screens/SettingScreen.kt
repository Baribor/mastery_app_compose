package com.cursydev.masteryhub.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cursydev.masteryhub.R
import com.cursydev.masteryhub.component.pref.PreferenceCategory
import com.cursydev.masteryhub.component.pref.SwitchPreference
import com.cursydev.masteryhub.ui.theme.MasteryHubTheme


@Composable
fun SettingScreen() {

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface)) {
        PreferenceCategory(title = "Updates") {
            SwitchPreference(title = "Blog update", key = stringResource(id = R.string.settings_blog_update), summary = "Stay connected with the latest blog", defaultValue = true)

            SwitchPreference(title = "Mastery Motivational", key = stringResource(id = R.string.settings_motivational_update), summary = "Receive Mastery Motivational updates")
        }


        PreferenceCategory(title = "Download") {
            SwitchPreference(title = "Media download", key = stringResource(id = R.string.settings_media_download), summary = "Automatically download media over wifi")
        }
    }
}


@Preview
@Composable
fun SettingScreenPreview() {
    MasteryHubTheme(darkTheme = true) {
        SettingScreen()
    }
}