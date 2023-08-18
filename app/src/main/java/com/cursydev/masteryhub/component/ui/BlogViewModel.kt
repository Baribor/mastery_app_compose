package com.cursydev.masteryhub.component.ui

import android.content.Context
import android.util.Log
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BlogViewModel(private val blogRepository: BlogRepository): ViewModel(), BackgroundProcessListener<BlogData> {

    var toolbarTitle = mutableStateOf("Blogs")
        private set
    var activeTabRoute = Screen.BlogScreens.MainBlogScreen.route
        private set

    var activeBlogDetail: BlogDetail? = null
        private set

    private val _blogs = MutableStateFlow(mutableListOf<BlogData>())
    var blogs : StateFlow<MutableList<BlogData>> =_blogs.asStateFlow()

    var isLoading = mutableStateOf(true)
        private set

    fun setTitle(title: String){
        toolbarTitle.value = title
    }

    override fun onFinish(result: ProcessStatus) {
        if(result.status == Status.FAIL){
            Log.d("retrieve", "An error occurred")
            isLoading.value = false
            return
        }

        val newItems = mutableListOf<BlogData>()
        result.itemsList.forEach {
            it as BlogData
            newItems.add(it)
        }
        /*blogs.addAll(newItems)*/
        Log.d("retrieve", "Retrieve done")
        viewModelScope.launch {
            blogRepository.insertBlogs(newItems)
            Log.d("retrieve", "Insert called")
            blogRepository.getAllBlogs().collect{ newBlogs ->
                _blogs.update{
                    newBlogs.toMutableList()
                }
            }
            isLoading.value = false

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
               blogRepository.getAllBlogs().collect{ newBlogs ->
                   _blogs.update{
                       newBlogs.toMutableList()
                   }
                Log.d("retrieve", "Finished setting from database")
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
