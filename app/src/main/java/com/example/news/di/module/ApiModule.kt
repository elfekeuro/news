package com.example.news.di.module

import com.example.news.BuildConfig.BASE_URL
import com.example.news.BuildConfig.DEBUG
import com.example.news.service.api.NewsService
import com.example.news.utils.Constant.Companion.SERVICE_TIMEOUT
import com.example.weatherforecast.service.RequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {
    single {
        create()
    }

    single {
        service(retrofit = get(), service = NewsService::class.java)
    }
}

fun create(
    baseUrl: String? = BASE_URL
): Retrofit {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY

    val okHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(RequestInterceptor())
        if (DEBUG)
            addNetworkInterceptor(logging)
        readTimeout(SERVICE_TIMEOUT, TimeUnit.SECONDS)
    }

    return Retrofit.Builder()
        .baseUrl(baseUrl ?: BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient.build())
        .build()
}

fun <T> service(retrofit: Retrofit, service: Class<T>): T {
    return retrofit.create(service)
}
