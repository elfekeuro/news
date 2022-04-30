package com.example.news.di.module

import com.example.news.scene.detail.DetailViewModel
import com.example.weatherforecast.base.BaseViewModelFactory
import com.example.news.scene.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        BaseViewModelFactory(
            MainViewModel(
                get()
            )
        ).create(MainViewModel::class.java)
    }
    viewModel {
        BaseViewModelFactory(
            DetailViewModel()
        ).create(DetailViewModel::class.java)
    }
}
