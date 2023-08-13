package com.cursydev.masteryhub.util

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.cursydev.masteryhub.MasteryApp

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