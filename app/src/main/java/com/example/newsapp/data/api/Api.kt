package com.example.newsapp.data.api

import com.example.newsapp.data.model.NewsResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface Api {
    companion object {
        const val API_KEY: String = "0e19994e657e449c96900ccab3794243"
    }

    @GET("top-headlines")
    @Headers("apiKey: ${API_KEY}", "country: id")
    suspend fun getNews(): Call<ArrayList<NewsResult>>
}