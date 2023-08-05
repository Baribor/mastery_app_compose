package com.cursydev.masteryhub

import android.app.Application
import com.cursydev.masteryhub.crash.CrashHandler

class MasteryApp: Application() {

    override fun onCreate() {
        super.onCreate()
        sApp = this
        CrashHandler().init(this)
    }


    companion object{
        private lateinit var sApp: MasteryApp

        fun getApp(): MasteryApp{
            return sApp
        }
    }
}