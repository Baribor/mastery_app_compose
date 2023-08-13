package com.cursydev.masteryhub.db

import android.content.Context

interface AppContainer {
    val blogRepository: BlogRepository
}


class AppDataContainer(private val context: Context): AppContainer{

    override val blogRepository: BlogRepository by lazy {
        BlogRepository(BlogDatabase.getDatabase(context).blogDao())
    }
}