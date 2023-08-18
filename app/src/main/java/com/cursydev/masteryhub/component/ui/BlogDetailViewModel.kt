package com.cursydev.masteryhub.component.ui

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cursydev.masteryhub.MasteryApp
import com.cursydev.masteryhub.util.BackgroundProcessListener
import com.cursydev.masteryhub.util.BlogData
import com.cursydev.masteryhub.util.BlogDetail
import com.cursydev.masteryhub.util.ProcessStatus
import com.cursydev.masteryhub.util.RetrieveBlogTask
import com.cursydev.masteryhub.util.Titleable
import com.cursydev.masteryhub.util.isNetworkAvailable

class BlogDetailViewModel: ViewModel(), BackgroundProcessListener<BlogDetail> {

    val blogDetail : MutableState<BlogDetail?> = mutableStateOf(null)
    lateinit var currentBlogData: BlogData
        private set

    override fun onFinish(result: ProcessStatus) {
        if(result.status == ProcessStatus.Companion.Status.FAIL){
            Log.d("retrieve", "Failed to get blog details")
            return
        }

        blogDetail.value = result.itemsList[0] as BlogDetail
    }

    override fun onProgress(item: Titleable) {

    }

    override fun getListenerContext(): Context {
        return MasteryApp.getApp()
    }

    fun setCurrentBlogData(data: BlogData){
        currentBlogData = data
    }


    fun startDetailFetch(){
        if(isNetworkAvailable()){
            RetrieveBlogTask(getListenerContext(), currentBlogData, this).execute()
        }
    }
}