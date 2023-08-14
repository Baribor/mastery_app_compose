package com.cursydev.masteryhub.component.ui

import android.content.Context
import android.text.BoringLayout
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursydev.masteryhub.db.BlogRepository
import com.cursydev.masteryhub.screens.Screen
import com.cursydev.masteryhub.util.BackgroundProcessListener
import com.cursydev.masteryhub.util.BlogData
import com.cursydev.masteryhub.util.BlogDetail
import com.cursydev.masteryhub.util.MasteryTasks
import com.cursydev.masteryhub.util.Model
import com.cursydev.masteryhub.util.ProcessStatus
import com.cursydev.masteryhub.util.ProcessStatus.Companion.Status
import com.cursydev.masteryhub.util.Titleable
import com.cursydev.masteryhub.util.isNetworkAvailable
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BlogViewModel(private val blogRepository: BlogRepository): ViewModel(), BackgroundProcessListener {

    var toolbarTitle = mutableStateOf("Blogs")
        private set
    var activeTabRoute = Screen.BlogScreens.MainBlogScreen.route
        private set

    var activeBlogDetail: BlogDetail? = null
        private set

    var blogs = mutableStateOf(listOf<BlogData>())
    var isLoading = mutableStateOf(true)
        private set

    fun setTitle(title: String){
        toolbarTitle.value = title
    }

    override fun onFinish(result: ProcessStatus) {
        if(result.status == Status.FAIL){
            Log.d("retrieval stat:", "An error occurred")
            isLoading.value = false
            return
        }

        blogs.value = result.itemsList as List<BlogData>
        isLoading.value = false
        viewModelScope.launch {
            blogRepository.insertBlogs(blogs.value)
        }
    }

    override fun onProgress(item: Titleable) {

    }

    override fun getListenerContext(): Context {
        return getListenerContext()
    }


    @OptIn(DelicateCoroutinesApi::class)
    fun startBlogFetch(){
        if(isNetworkAvailable()){
            MasteryTasks(Model.Blog, this).execute()
        }else{

            viewModelScope.launch {
               blogRepository.getAllBlogs().collect{
                blogs.value = it
                Log.d("retrieval stat:", "Finished setting from database")
               }
            }
        }
    }


    fun setActiveTabRoute(route: String){
        activeTabRoute = route
    }

    fun setActiveBlogDetail(blogDetail: BlogDetail){
        activeBlogDetail = blogDetail
    }

}