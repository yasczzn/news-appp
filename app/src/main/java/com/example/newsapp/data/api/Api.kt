package com.example.newsapp.data.api

import com.example.newsapp.data.model.NewsResult
import com.example.newsapp.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country")
        country: String = "id",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResult>

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResult>
}