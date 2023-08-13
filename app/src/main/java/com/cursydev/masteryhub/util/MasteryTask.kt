package com.cursydev.masteryhub.util

import kotlinx.coroutines.DelicateCoroutinesApi
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

@DelicateCoroutinesApi
class MasteryTasks(
    private val model: Model,
    private val listener: BackgroundProcessListener,
    val name: String = ""
) :
    CoroutinesAsyncTask<String, Void, ProcessStatus>(name) {

    lateinit var item: Titleable
    private val result = ProcessStatus()


    override fun doInBackground(vararg params: String?): ProcessStatus {

        try {
            val doc = Jsoup.connect(
                when (params.isNotEmpty()) {
                    true -> params[0]
                    else -> getUrl(model)
                }
            ).get()

            val items = doc.getElementsByClass(getContainerClassName(model))
            items.forEach {
                createElementData(it)
            }


        } catch (e: Exception) {
            result.status = ProcessStatus.Companion.Status.FAIL
        }
        return result
    }

    private fun createElementData(element: Element) {
        when (model.modelType) {
            ModelType.BLOG -> {

                var imgSrc = ""
                try {
                    imgSrc = element.getElementsByTag("img")[0].attr("src")
                } catch (_: Exception) {
                }

                item = BlogData(
                    element.getElementsByClass("blog-post-title")[0].text(),
                    imgSrc,
                    element.getElementsByTag("p")[0].text(),
                    element.getElementsByTag("a")[0].attr("href")
                )
                result.itemsList.add(item)

            }

            /*DataBaseType.MUSIC -> {

                item = Music(
                    element.getElementsByTag("span")[0].text(),
                    src = element.getElementsByTag("a")[0].attr("href"),
                    imgSrc = element.getElementsByTag("img")[0].attr("src")
                )
                result.itemsList.add(item)

            }

            DataBaseType.VIDEO -> {
                item = Video(
                    element.getElementsByTag("span")[0].text(),
                    src = element.getElementsByTag("iframe")[0].attr("src")
                )
                result.itemsList.add(item)

            }*/

            else -> return
        }
    }


    override fun onPostExecute(result: ProcessStatus?) {
        listener.onFinish(result!!)
    }

    override fun onProgressUpdate(vararg values: Void?) {
        listener.onProgress(item)
    }


    companion object {
        const val BASE_URL = "https://masterymedia.com.ng/"

        fun getUrl(model: Model): String = "$BASE_URL${model.relativeUrl}"

        fun getContainerClassName(model: Model): String = model.containerClass
    }
}