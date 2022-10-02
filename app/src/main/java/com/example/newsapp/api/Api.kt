package com.example.newsapp.api

import com.example.newsapp.model.NewsList
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("top-headlines")
    suspend fun getNews(): Call<ArrayList<NewsList>>
}