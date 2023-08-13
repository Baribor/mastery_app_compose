package com.cursydev.masteryhub.util

import androidx.compose.runtime.Composable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "blog_table")
data class BlogData( val mTitle:String, val imgUrl: String, val desc: String, val fullUrl: String, @PrimaryKey(autoGenerate = true) val id :Int = 0):Titleable {
    @Ignore
    override fun getTitle(): String = mTitle
}

data class BlogDetail(
    val mTitle: String,
    var author: String,
    var authorImgSrc: String,
    var date: String,
    var body: @Composable (()->Unit)? = null,
    var success: Boolean = true
)


enum class ModelType{
    MUSIC,
    VIDEO,
    BLOG
}


sealed class Model(val relativeUrl: String, val containerClass: String, val modelType: ModelType){
    object Blog: Model("blog/", "blog-con", ModelType.BLOG)
    object Video: Model("videos/", "Video-SB-White", ModelType.VIDEO)
    object Music: Model("audio/", "Audio-Container", ModelType.MUSIC)
}