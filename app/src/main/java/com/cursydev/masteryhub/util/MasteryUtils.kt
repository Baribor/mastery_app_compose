package com.cursydev.masteryhub.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cursydev.masteryhub.MasteryApp
import com.cursydev.masteryhub.R
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Locale

fun isNetworkAvailable(): Boolean {
    val connectivityManager =
        MasteryApp.getApp().applicationContext.getSystemService(ConnectivityManager::class.java)
    val currentNetwork = connectivityManager.activeNetwork
    val networkCapabilities = connectivityManager.getNetworkCapabilities(currentNetwork)
    networkCapabilities?.let {
        if (it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
            return true
        }
    }
    return false
}



fun saveBlog(html: Element, name: String, ctx: Context) {
    try {
        val fileOutPutStream = ctx.openFileOutput(name, Context.MODE_PRIVATE)
        val outputStreamWriter = OutputStreamWriter(fileOutPutStream)
        outputStreamWriter.write(html.outerHtml())
        outputStreamWriter.close()
    } catch (_: Exception) {
    }
}

fun retrieveBlog(name: String, ctx: Context): Element {
    val stringBuilder = StringBuilder()
    try {
        val fileInputStream = ctx.openFileInput(name)
        val inputStreamReader = InputStreamReader(fileInputStream)
        val bufferReader = BufferedReader(inputStreamReader)
        bufferReader.forEachLine {
            stringBuilder.append(it)
        }
    } catch (_: Exception) {
    }

    return Jsoup.parse(stringBuilder.toString())
}



fun getDate(date: String): String {
    val dateFormatted = date.substring(date.indexOf("on") + 2).trim()
    val month = "[a-zA-Z]+".toRegex().find(dateFormatted)?.value
    val dayYear = "[0-9]+".toRegex().findAll(dateFormatted)
    dayYear.elementAt(2).next()
    return "${dayYear.first().value}/${month?.toIntMonth()}/${dayYear.first().next()?.value}"
}

fun String.toIntMonth(): String {
    return when (this.lowercase(Locale.ROOT)) {
        "january" -> "01"
        "february" -> "02"
        "march" -> "03"
        "april" -> "04"
        "may" -> "05"
        "june" -> "06"
        "july" -> "07"
        "august" -> "08"
        "september" -> "09"
        "october" -> "10"
        "november" -> "11"
        else -> "12"
    }

}
