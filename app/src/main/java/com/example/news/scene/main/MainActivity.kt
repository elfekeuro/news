package com.example.news.scene.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import com.example.news.base.BaseActivity
import com.example.news.databinding.ActivityMainBinding
import com.example.news.extension.intentToAnotherActivity
import com.example.news.extension.toast
import com.example.news.model.NewsModel
import com.example.news.scene.detail.DetailActivity
import com.example.news.scene.detail.DetailActivity.Companion.NEWS_MODEL
import com.example.news.scene.main.adapter.NewsAdapter
import com.example.weatherforecast.service.event.NetworkEvent
import org.koin.android.ext.android.get

class MainActivity : BaseActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by lazy { get() }
    private var newsAdapter: NewsAdapter? = null

    override fun getViewBinding(): View {
        return binding.root
    }

    override fun initialView() {
        viewModel.getNews("today")
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getNews(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    override fun observerData() {
        viewModel.newsResponse.observe(this) { event ->
            when (event) {
                is NetworkEvent.Loading -> {
                    showLoading(event)
                }
                is NetworkEvent.Success -> {
                    showNews(event.data as ArrayList<NewsModel>)
                }
                is NetworkEvent.Failure -> {
                    toast(event.message)
                }
            }
        }
    }

    private fun showNews(list: ArrayList<NewsModel>) {
        newsAdapter = NewsAdapter(list).apply {
            onItemClickListener = { item ->
                intentToAnotherActivity(DetailActivity::class.java, Bundle().apply {
                    this.putParcelable(NEWS_MODEL, item)
                })
            }
        }
        binding.recyclerView.adapter = newsAdapter
    }

    private fun showLoading(event: NetworkEvent.Loading) {
        if (event.isShow) {
            View.VISIBLE.also {
                binding.progress.visibility = it
            }
        } else {
            View.GONE.also {
                binding.progress.visibility = it
            }
        }
    }
}