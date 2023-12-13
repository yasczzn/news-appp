package com.example.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.NewsResult
import com.example.newsapp.databinding.ItemNewsBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    class NewsViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    private var onItemClickListener: ((Article) -> Unit)? = null

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]

        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(holder.binding.imgNews)
            holder.binding.tvNews.text = article.title
            holder.binding.tvSource.text = article.source.name
            holder.binding.tvDesc.text = article.description
            holder.binding.tvDatetime.text = article.publishedAt

            setOnClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }

}


