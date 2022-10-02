package com.example.newsapp.model

data class NewsList(

    val apikey: String? = "0e19994e657e449c96900ccab3794243",
    val country: String = "id",
    val category: String = "technology",
    val sources: String,
    val q: String,
    val pageSize: Int,
    val page: Int

)
