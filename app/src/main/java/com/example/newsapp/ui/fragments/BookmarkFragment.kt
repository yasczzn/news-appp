package com.example.newsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapp.databinding.FragmentBookmarkBinding
import com.example.newsapp.ui.adapter.NewsAdapter
import com.example.newsapp.ui.viewModel.MainViewModel

class BookmarkFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: NewsAdapter

    private var _binding: FragmentBookmarkBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root

    }


}