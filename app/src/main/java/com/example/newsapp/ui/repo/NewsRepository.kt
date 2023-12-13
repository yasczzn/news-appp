package com.example.newsapp.ui.repo

import com.example.newsapp.data.api.RetrofitInstance
import com.example.newsapp.data.db.ArticleDatabase
import com.example.newsapp.data.model.Article

class NewsRepository(val db: ArticleDatabase) {

    suspend fun getHeadlines(country: String, pageNumber: Int) =
        RetrofitInstance.api.getNews(country, pageNumber)

    suspend fun getEverything(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getFavoriteNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}