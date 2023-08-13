package com.cursydev.masteryhub.util

import android.content.Context

interface Titleable {
    fun getTitle():String
}



interface BackgroundProcessListener{
    fun onFinish(result:ProcessStatus)
    fun onProgress(item:Titleable)
    fun getListenerContext(): Context
}