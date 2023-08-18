package com.cursydev.masteryhub.util

import android.content.Context
import kotlinx.coroutines.DelicateCoroutinesApi
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

@DelicateCoroutinesApi
class MasteryTasks<T : Titleable>(
    private val model: Model,
    private val listener: BackgroundProcessListener<T>,
    val name: String = ""
) :
    CoroutinesAsyncTask<String, Titleable, ProcessStatus>(name) {

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

    override fun onProgressUpdate(vararg values: Titleable?) {
        listener.onProgress(item)
    }


    companion object {
        const val BASE_URL = "https://masterymedia.com.ng/"

        fun getUrl(model: Model): String = "$BASE_URL${model.relativeUrl}"

        fun getContainerClassName(model: Model): String = model.containerClass
    }
}



class RetrieveBlogTask(private val ctx: Context, private val blogData: BlogData, private val listener: BackgroundProcessListener<BlogDetail>) :
    CoroutinesAsyncTask<Void, Void, ProcessStatus>("") {

    private val result = ProcessStatus()
    override fun onPreExecute() {
        //Show the loading screen
    }

    override fun doInBackground(vararg params: Void?): ProcessStatus {
        val detail = BlogDetail("", "", "", "")
        try {
            val doc = Jsoup.connect(blogData.fullUrl).get()
            detail.author = doc.getElementsByClass("author-name")[0].text()
            detail.authorImgSrc = doc.getElementsByClass("blog-author-img")[0].attr("src")
            detail.date = getDate(doc.getElementsByClass("author-date")[0].text())
            val element = doc.getElementsByClass("blog-post-content")[0]
            saveBlog(element, blogData.fullUrl, ctx)
            detail.body = element
            result.itemsList.add(detail)
        } catch (e: Exception) {
            result.status = ProcessStatus.Companion.Status.FAIL
        }

        return result
    }

    override fun onPostExecute(result: ProcessStatus?) {
        listener.onFinish(result!!)
    }
}
