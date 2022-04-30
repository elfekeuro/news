package com.example.news.scene.detail

import android.view.View
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.base.BaseActivity
import com.example.news.databinding.ActivityDetailBinding
import com.example.news.extension.toDateUpdatedFormat
import com.example.news.model.NewsModel
import org.koin.android.ext.android.get

class DetailActivity : BaseActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }
    companion object{
        const val NEWS_MODEL = "NEWS_MODEL"
    }

    private val viewModel: DetailViewModel by lazy { get() }

    override fun getViewBinding(): View {
        return binding.root
    }

    override fun initialView() {
        val newsModel = intent?.extras?.getParcelable<NewsModel>(NEWS_MODEL)
        showNews(newsModel)

        binding.layoutBack.setOnClickListener {
            onBackPressed()
        }

    }

    override fun observerData() {

    }

    private fun showNews(newsModel: NewsModel?) {
        newsModel?.let{
            Glide.with(binding.root.context)
                .load(it.urlToImage)
                .into(binding.imPic)
            binding.tvTitle.text = it.title
            binding.tvContent.text = it.content

            binding.tvUpdate.text = String.format(
                binding.root.context.getString(R.string.updated),
                it.publishedAt?.toDateUpdatedFormat()
            )
        }
    }

}