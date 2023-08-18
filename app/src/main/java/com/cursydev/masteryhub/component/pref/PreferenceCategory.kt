package com.cursydev.masteryhub.component.pref

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cursydev.masteryhub.ui.theme.MasteryHubTheme


@Composable
fun PreferenceCategory(title:String, content: @Composable ()->Unit) {

    Column(modifier = Modifier.fillMaxWidth().padding(top = 18.dp)) {

        Text(text = title, modifier = Modifier.padding(start = 12.dp, bottom = 4.dp), color = MaterialTheme.colorScheme.secondary)
        content()
    }
}


@Preview
@Composable
fun PreferenceCategoryPreview() {

    MasteryHubTheme(darkTheme = false) {
        PreferenceCategory(title = "Updates") {
            SwitchPreference(title = "Blog update", key = "blog_update_key", summary = "Stay connected with the latest blog", defaultValue = false)
            SwitchPreference(title = "Mastery Motivational", key = "blog_update_key", summary = "Receive Mastery Motivational updates", defaultValue = false)
        }
    }
}