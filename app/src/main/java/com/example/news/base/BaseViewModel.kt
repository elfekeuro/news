package com.example.news.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

    val job = Job()

    var dispatcherIO: CoroutineDispatcher = Dispatchers.IO
        set(dispatcherIO) {
            field = dispatcherIO
            scopeIO = CoroutineScope(job + dispatcherIO)
        }

    private var scopeIO: CoroutineScope? = null
        get() =
            field ?: run {
                CoroutineScope(job + dispatcherIO)
            }

    override fun onCleared() {
        job.cancel()
        scopeIO?.cancel()
        dispatcherIO.cancel()
        super.onCleared()
    }

    suspend fun <T>callService(function:()->T?):T? = scopeIO?.coroutineContext?.let {withContext(it){function()} }

    override val coroutineContext: CoroutineContext
        get() = job+Dispatchers.Main
}