package com.cursydev.masteryhub.util

class Constants {
    enum class Status{
        PENDING,
        RUNNING,
        FINISHED
    }
}





data class ProcessStatus(var status:Status=Status.SUCCESS, val itemsList:MutableList<Titleable> = mutableListOf()){
    companion object{
        enum class Status{
            SUCCESS,
            FAIL
        }
    }
}