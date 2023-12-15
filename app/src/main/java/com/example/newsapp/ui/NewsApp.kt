package com.example.newsapp.ui

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.data.db.ArticleDatabase
import com.example.newsapp.databinding.ActivityNewsBinding
import com.example.newsapp.ui.repo.NewsRepository


class NewsApp : AppCompatActivity() {

    lateinit var newsViewModel: NewsViewModel
    lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(application, newsRepository)
        newsViewModel = ViewModelProvider(this, viewModelProviderFactory)[NewsViewModel::class.java]

        val navController = (supportFragmentManager.findFragmentById(com.example.newsapp.R.id.newsNavHostFragment) as NavHostFragment).navController
        binding.bottomNav.setupWithNavController(navController)
    }

}