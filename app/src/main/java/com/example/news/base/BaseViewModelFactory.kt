package com.example.weatherforecast.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.news.base.BaseViewModel

class BaseViewModelFactory(private val baseViewModel: BaseViewModel) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return baseViewModel as T
    }
}