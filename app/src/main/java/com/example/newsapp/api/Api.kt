package com.example.newsapp.api

import com.example.newsapp.model.NewsList
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("/top-headlines?country=us&apiKey=API_KEY")
    suspend fun getNews(): Response<NewsList>
}