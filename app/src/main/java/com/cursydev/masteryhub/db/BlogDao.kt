package com.cursydev.masteryhub.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.cursydev.masteryhub.util.BlogData
import kotlinx.coroutines.flow.Flow

@Dao
interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBlogs(blog: List<BlogData>)

    @Update
    suspend fun update(blogData: BlogData)

    @Query("SELECT * FROM blog_table")
    fun getAllBlogs(): Flow<List<BlogData>>
}