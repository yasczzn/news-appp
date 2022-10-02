package com.example.newsapp.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.newsapp.data.repository.MainRepo
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val mainRepo: MainRepo) : ViewModel() {

    fun getNews() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepo.getNews()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}