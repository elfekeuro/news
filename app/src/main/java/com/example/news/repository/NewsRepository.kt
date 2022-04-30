package com.example.news.repository

import com.example.news.base.BaseRepository
import com.example.news.service.api.NewsService

class NewsRepository(private val api: NewsService) : BaseRepository() {
    fun getNews(
        word: String? = null
    ) = responseAdapter(api.getNews(word))
}