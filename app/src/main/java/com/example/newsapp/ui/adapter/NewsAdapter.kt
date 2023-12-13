package com.example.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.data.model.NewsResult
import com.example.newsapp.databinding.ItemNewsBinding

class NewsAdapter(private val newsList: List<NewsResult>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemBinding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(itemBinding)
    }

    class NewsViewHolder(private val itemBinding: ItemNewsBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(newsResult: NewsResult) {
            itemBinding.tvNews.text = newsResult.articles.toString()
            itemBinding.tvSource.text = newsResult.articles.toString()
        }
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsResult: NewsResult = newsList[position]
        holder.bind(newsResult)
    }

    override fun getItemCount(): Int = newsList.size

}


