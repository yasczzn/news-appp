package com.example.newsapp.model

data class NewsResult(

    val status: String,
    val totalResults: Int,
    val articles: List<Articles>

)