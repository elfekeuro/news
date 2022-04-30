package com.example.news

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.example.news.di.module.apiModule
import com.example.news.di.module.repositoryModule
import com.example.news.di.module.viewModelModule
import com.orhanobut.hawk.Hawk
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class NewsApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context : Context
    }

    override fun onCreate() {
        super.onCreate()
        context = baseContext
        Hawk.init(this).build()

        startKoin{
            androidLogger(Level.NONE)
            androidContext(this@NewsApplication)
            modules(apiModule)
            modules(repositoryModule)
            modules(viewModelModule)
        }

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}