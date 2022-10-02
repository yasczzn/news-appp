package com.example.newsapp.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.data.api.ApiHelper
import com.example.newsapp.data.repository.MainRepo

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepo(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}