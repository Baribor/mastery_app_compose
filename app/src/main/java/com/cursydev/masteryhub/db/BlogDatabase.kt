package com.cursydev.masteryhub.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cursydev.masteryhub.util.BlogData

@Database(entities = [BlogData::class], version = 1, exportSchema = false)
abstract class BlogDatabase: RoomDatabase() {

    abstract fun blogDao():BlogDao

    companion object{

        @Volatile
        private var Instance: BlogDatabase? = null

        fun getDatabase(context: Context): BlogDatabase{
            return Instance?: synchronized(this){
                Room.databaseBuilder(context, BlogDatabase::class.java, "blog_database")
                    .fallbackToDestructiveMigration() //add a migration strategy
                    .build()
                    .also { Instance = it }
            }
        }
    }
}