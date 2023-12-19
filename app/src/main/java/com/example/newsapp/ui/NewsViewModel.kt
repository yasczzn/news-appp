package com.example.newsapp.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.NewsResult
import com.example.newsapp.ui.repo.NewsRepository
import com.example.newsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class NewsViewModel(app: Application, val newsRepository: NewsRepository): AndroidViewModel(app) {

    val newsList: MutableLiveData<Resource<NewsResult>> = MutableLiveData()
    var newsListPage = 1
    var newsListResponse: NewsResult? = null

    val searchNews: MutableLiveData<Resource<NewsResult>> = MutableLiveData()
    var searchNewsPage = 1
    var searchNewsResponse: NewsResult? = null
    var newSearchQuery: String? = null
    var oldSearchQuery: String? = null

    init {
        getNewsList("id")
    }

    fun getNewsList(countryCode: String) = viewModelScope.launch {
        newsListInternet(countryCode)
    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        searchNewsInternet(searchQuery)
    }

    private fun handleNewsListResponse(response: Response<NewsResult>): Resource<NewsResult> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                newsListPage++
                if (newsListResponse == null) {
                    newsListResponse = resultResponse
                } else {
                    val oldArticles = newsListResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(newsListResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<NewsResult>): Resource<NewsResult> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if (searchNewsResponse == null || newSearchQuery != oldSearchQuery) {
                    searchNewsPage = 1
                    oldSearchQuery = newSearchQuery
                    searchNewsResponse = resultResponse
                } else {
                    searchNewsPage++
                    val oldArticles = searchNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(searchNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun addToBookmark(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

    fun getBookmark() = newsRepository.getBookmark()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }

    fun internetConnection(context: Context): Boolean {
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
            return getNetworkCapabilities(activeNetwork)?.run {
                when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            } ?: false
        }
    }

    private suspend fun newsListInternet(countryCode: String) {
        newsList.postValue(Resource.Loading())
        try {
            if (internetConnection(this.getApplication())) {
                val response = newsRepository.getNewsList(countryCode, newsListPage)
                newsList.postValue(handleNewsListResponse(response))
            } else {
                newsList.postValue(Resource.Error("No internet connection"))
            }
        } catch (t: Throwable) {
            when(t) {
                is IOException -> newsList.postValue(Resource.Error("Unable to connect"))
                else -> newsList.postValue(Resource.Error("No signal"))
            }
        }
    }

    private suspend fun searchNewsInternet(searchQuery: String) {
        newSearchQuery = searchQuery
        searchNews.postValue(Resource.Loading())
        try {
            if (internetConnection(this.getApplication())) {
                val response = newsRepository.getEverything(searchQuery, searchNewsPage)
                searchNews.postValue(handleNewsListResponse(response))
            } else {
                searchNews.postValue(Resource.Error("No internet connection"))
            }
        } catch (t: Throwable) {
            when(t) {
                is IOException -> searchNews.postValue(Resource.Error("Unable to connect"))
                else -> searchNews.postValue(Resource.Error("No signal"))
            }
        }
    }
}