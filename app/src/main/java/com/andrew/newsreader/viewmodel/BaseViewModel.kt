package com.andrew.newsreader.viewmodel

import androidx.lifecycle.ViewModel
import com.andrew.newsreader.data.Item
import com.andrew.newsreader.util.SingleLiveEvent
import kotlinx.coroutines.*

abstract class BaseViewModel: ViewModel() {
    val backgroundScope = CoroutineScope(Dispatchers.IO + Job() + errorHandler)
    val mainScope = CoroutineScope(Dispatchers.Main + Job() + errorHandler)

    val parsingComplete = SingleLiveEvent<Item>()

    override fun onCleared() {
        mainScope.cancel()
        backgroundScope.cancel()
        super.onCleared()
    }

    abstract fun onCreate()

    companion object {
        val errorHandler = CoroutineExceptionHandler { _, error ->
            error.printStackTrace()
        }
    }
}