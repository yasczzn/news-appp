package com.example.newsapp.data.repository

import com.example.newsapp.data.api.ApiHelper

class MainRepo(private val apiHelper: ApiHelper) {

    suspend fun getNews() = apiHelper.getNews()
}