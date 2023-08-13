package com.cursydev.masteryhub.db

import com.cursydev.masteryhub.util.BlogData
import kotlinx.coroutines.flow.Flow

class BlogRepository(private val blogDao: BlogDao) {

    suspend fun insertBlogs(blogs: List<BlogData>) = blogDao.insertBlogs(blogs)

    fun getAllBlogs():Flow<List<BlogData>> = blogDao.getAllBlogs()
}