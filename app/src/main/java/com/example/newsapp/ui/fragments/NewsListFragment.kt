package com.example.newsapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment

import android.view.View
import android.widget.AbsListView
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsListBinding
import com.example.newsapp.ui.NewsApp
import com.example.newsapp.ui.NewsViewModel
import com.example.newsapp.ui.adapter.NewsAdapter
import com.example.newsapp.util.Constants

import retrofit2.Call

class NewsListFragment : Fragment() {

    lateinit var newsViewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    lateinit var retryButton: Button
    lateinit var errorText: TextView
    lateinit var itemNewsError: CardView
    lateinit var binding: FragmentNewsListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsListBinding.bind(view)
        itemNewsError = binding.itemNewsError.root

        val inflater = requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.item_error, null)

        retryButton = binding.itemNewsError.retryButton
        errorText = binding.itemNewsError.errorText

        newsViewModel = (activity as NewsApp).newsViewModel
        setupHeadlinesRecycler()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(R.id.action_newsListFragment_to_detailsFragment, bundle)

        }
    }

    var isError = false
    var isLoading = false
    var isLastPage = false
    var isScroll = false

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        isLoading = true
    }

    private fun hideErrorMsg() {
        itemNewsError.visibility = View.INVISIBLE
        isError = false
    }

    private fun showErrorMsg(message: String) {
        itemNewsError.visibility = View.VISIBLE
        errorText.text = message
        isError = true
    }

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotError = !isError
            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItem + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItem >= 0
            val isTotalMoreThanVisible = totalItemCount >= Constants.QUERY_PAGE_SIZE
            val shouldPaginate = isNotError && isNotLoadingAndNotLastPage && isAtLastItem
                    && isNotAtBeginning && isTotalMoreThanVisible && isScroll

            if (shouldPaginate) {
                newsViewModel.getNewsList("id")
                isScroll = false
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScroll = true
            }
        }
    }

    private fun setupHeadlinesRecycler() {
        newsAdapter = NewsAdapter()
        binding.rvMain.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@NewsListFragment.scrollListener)
        }
    }

}

