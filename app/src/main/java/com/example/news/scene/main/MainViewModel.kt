package com.example.news.scene.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.news.base.BaseViewModel
import com.example.news.repository.NewsRepository
import com.example.weatherforecast.service.event.NetworkEvent
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.util.*

class MainViewModel(
    private val repository: NewsRepository
) : BaseViewModel() {

    private var _newResponse: MutableLiveData<NetworkEvent> = MutableLiveData()
    var newsResponse: LiveData<NetworkEvent> = _newResponse

    fun getNews(word: String? = null) = launch {
        _newResponse.value = NetworkEvent.Loading(true)
        val response = callService {
            repository.getNews(word = word)
        }
        response?.let {
            if (it.code?.toInt() == HttpURLConnection.HTTP_OK) {
                _newResponse.value = NetworkEvent.Success(it.articles)
            } else {
                _newResponse.value = NetworkEvent.Failure(it.message.toString())
            }
        }
        _newResponse.value = NetworkEvent.Loading(false)
    }

    fun findMiddleIndex(array: Array<Int>): String {
        var sum = 0
        var leftsum = 0
        for (x in array) sum += x
        for (i in array.indices) {
            if (leftsum == sum - leftsum - array[i]) return "middle index is $i"
            leftsum += array[i]
        }
        return "index not found"
    }

    fun isPalindrome(word:String): Boolean {
        val strLength = word.length
        var reverseStr = ""

        for (i in strLength - 1 downTo 0) {
            reverseStr += word[i]
        }
        return word.lowercase(Locale.getDefault()) == reverseStr.lowercase(Locale.getDefault())
    }
}