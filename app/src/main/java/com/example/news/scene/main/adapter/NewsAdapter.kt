package com.example.news.scene.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.databinding.ItemNewsBinding
import com.example.news.extension.toDateUpdatedFormat
import com.example.news.model.NewsModel

class NewsAdapter(var list: ArrayList<NewsModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemClickListener: ((newsModel: NewsModel) -> Unit)? = null

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val worktableViewHolder = viewHolder as NewsViewHolder
        worktableViewHolder.bind(position)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemNewsBinding.inflate(layoutInflater, viewGroup, false)
        return NewsViewHolder(binding)
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            Glide.with(binding.root.context)
                .load(list[position].urlToImage)
                .into(binding.imPic)
            binding.tvTitle.text = list[position].title
            binding.tvContent.text = list[position].description

            binding.tvUpdate.text = String.format(
                binding.root.context.getString(R.string.updated),
                list[position].publishedAt?.toDateUpdatedFormat()
            )
            binding.layout.setOnClickListener {
                onItemClickListener?.invoke(list[position])
            }
        }
    }

}