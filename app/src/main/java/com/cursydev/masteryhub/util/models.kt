package com.cursydev.masteryhub.util

import androidx.compose.runtime.Composable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.jsoup.nodes.Element

@Entity(tableName = "blog_table", primaryKeys = ["mTitle", "fullUrl"])
data class BlogData( val mTitle:String, val imgUrl: String, val desc: String, val fullUrl: String):Titleable {
    @Ignore
    override fun getTitle(): String = mTitle
}

data class BlogDetail(
    val mTitle: String,
    var author: String,
    var authorImgSrc: String,
    var date: String,
    var body: Element? = null,
    var success: Boolean = true
):Titleable{
    override fun getTitle(): String = mTitle
}

fun BlogData.toBlogDetail():BlogDetail = BlogDetail(mTitle, "Bassey Nton Nton", "Some author image link", "02/08/2023")


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