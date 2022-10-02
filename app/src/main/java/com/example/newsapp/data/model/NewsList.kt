package com.example.newsapp.data.model

data class NewsList(

    val country: String = "id",
    val category: String = "technology",
    val sources: String,
    val q: String,
    val pageSize: Int,
    val page: Int

)
