package com.example.news.di.module

import com.example.news.repository.NewsRepository
import org.koin.dsl.module

val repositoryModule = module {

    single {
        NewsRepository(get())
    }
}
