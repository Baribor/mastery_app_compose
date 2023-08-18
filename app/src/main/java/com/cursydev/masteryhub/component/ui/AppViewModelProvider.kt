package com.cursydev.masteryhub.component.ui

import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.cursydev.masteryhub.MasteryApp

object AppViewModelProvider {

    val Factory = viewModelFactory {

        initializer {
            BlogViewModel(MasteryApp.getApp().container.blogRepository)
        }

        initializer {
            BlogDetailViewModel()
        }
    }
}
