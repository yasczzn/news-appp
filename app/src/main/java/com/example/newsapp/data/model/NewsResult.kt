package com.example.newsapp.data.model

data class NewsResult(

    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int

)