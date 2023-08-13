package com.cursydev.masteryhub.component.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MediaViewModel: ViewModel() {

    var toolbarTitle = mutableStateOf("Videos")
    private set

    fun setTitle(title: String){
        toolbarTitle.value = title
    }
}