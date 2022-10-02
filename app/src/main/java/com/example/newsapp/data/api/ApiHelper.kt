package com.example.newsapp.data.api

class ApiHelper(private val api: Api) {

    suspend fun getNews() = api.getNews()
}