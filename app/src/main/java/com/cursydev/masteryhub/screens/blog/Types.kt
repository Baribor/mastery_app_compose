package com.cursydev.masteryhub.screens.blog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cursydev.masteryhub.R
import org.jsoup.nodes.Element

enum class ElemType{
    TEXT,
    IMAGE
}


abstract class LayoutType(open val type: ElemType)


data class TextElem(val text: String, override val type: ElemType = ElemType.TEXT):LayoutType(type)
data class ImageElem(val url: String, val caption: String? = null, override val type: ElemType = ElemType.IMAGE):LayoutType(type)



fun Element.getLayoutElems():List<LayoutType>{
    val elems = mutableListOf<LayoutType>()
    val paragraphText = StringBuilder("")

    allElements.forEach{ elem->

        when(elem.normalName()){
            "p"->{
                paragraphText.appendLine()
                paragraphText.appendLine(elem.text())
            }

            "figure" ->{
                val img = elem.selectFirst("img")
                val caption = elem.selectFirst("figcaption")
                elems.add(TextElem(paragraphText.toString()))
                paragraphText.clear()
                paragraphText.append("")

                elems.add(ImageElem(img.attr("src"), caption?.text()))

            }
        }
        elems.add(TextElem(paragraphText.toString()))
    }
    return elems.toList()
}