package com.cursydev.masteryhub.component.pref

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.preference.PreferenceManager
import com.cursydev.masteryhub.MasteryApp
import com.cursydev.masteryhub.ui.theme.MasteryHubTheme


@Composable
fun SwitchPreference(title:String, key: String, summary: String = "", defaultValue: Boolean = false) {

    var checked by remember {
        mutableStateOf(PreferenceManager.getDefaultSharedPreferences(MasteryApp.getApp()).getBoolean(key, defaultValue))
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            checked = !checked
            with(PreferenceManager.getDefaultSharedPreferences(MasteryApp.getApp()).edit()){
                putBoolean(key, checked)
                apply()
            }
        }) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface, style = MaterialTheme.typography.titleSmall)
                Text(text = summary, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurface)
            }
            Switch(checked = checked, onCheckedChange = null)
        }

    }
}

@Preview
@Composable
fun SwitchPreferencePreview() {
    MasteryHubTheme(darkTheme = false) {
        SwitchPreference(title = "Blog update", key = "blog_update_key", summary = "Stay connected with the latest blog", defaultValue = false)
    }
}