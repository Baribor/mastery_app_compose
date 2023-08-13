package com.cursydev.masteryhub

import android.app.Application
import com.cursydev.masteryhub.crash.CrashHandler
import com.cursydev.masteryhub.db.AppContainer
import com.cursydev.masteryhub.db.AppDataContainer

class MasteryApp: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        sApp = this
        //CrashHandler().init(this)
        container = AppDataContainer(this)
    }


    companion object{
        private lateinit var sApp: MasteryApp

        fun getApp(): MasteryApp{
            return sApp
        }
    }
}