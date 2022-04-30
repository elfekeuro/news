package com.example.news.service.api

import com.example.news.base.BaseResponse
import com.example.news.model.NewsModel
import com.example.news.utils.Constant.Companion.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("everything")
    fun getNews(
        @Query("q") word: String? = null,
        @Query("sortBy") sortBy: String? = "publishedAt",
        @Query("apiKey") apiKey: String? = API_KEY
    ): Call<BaseResponse<NewsModel>>

}