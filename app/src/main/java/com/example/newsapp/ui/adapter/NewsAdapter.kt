package com.example.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.data.model.NewsResult
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(val list: ArrayList<NewsResult>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(newsResult: NewsResult){
            itemView.apply {
                tv_news.text = newsResult.articles.toString()
                tv_source.text = newsResult.articles.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.NewsViewHolder =
        NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false))

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
            holder.bind(list[position])
        }

        fun addUsers(list: List<NewsResult>) {
            this.list.apply {
                clear()
                addAll(list)
            }

        }
}