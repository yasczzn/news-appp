package com.example.newsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapp.databinding.FragmentNewsListBinding
import com.example.newsapp.data.model.NewsResult
import com.example.newsapp.ui.adapter.NewsAdapter
import com.example.newsapp.ui.viewModel.MainViewModel

import retrofit2.Call

class NewsListFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: NewsAdapter

    private var _binding: FragmentNewsListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root

        setupUI()
        setupObservers()
    }



    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(itemView, savedInstanceState)
//        rv_main.apply {
//            // set a LinearLayoutManager to handle Android
//            // RecyclerView behavior
//            setHasFixedSize(true)
//            layoutManager = LinearLayoutManager(activity)
//        }

    }

//    private fun setupViewModel() {
//        viewModel = ViewModelProviders.of(
//            this,
//            ViewModelFactory(ApiHelper(RetrofitClient.api))
//        ).get(MainViewModel::class.java)
//    }

    private fun setupUI() {
//        rv_main.layoutManager = LinearLayoutManager(activity)
//        adapter = NewsAdapter(arrayListOf())
//        rv_main.addItemDecoration(
//            DividerItemDecoration(
//                rv_main.context,
//                (rv_main.layoutManager as LinearLayoutManager).orientation
//            )
//        )
//        rv_main.adapter = adapter
    }

    private fun setupObservers() {
//        viewModel.getNews().observe(viewLifecycleOwner, Observer {
//            it?.let { resource ->
//                when (resource.status) {
//                    Status.SUCCESS -> {
//                        rv_main.visibility = View.VISIBLE
//                        progressBar.visibility = View.GONE
//                        resource.data?.let { list -> retrieveList(list) }
//                    }
//                    Status.ERROR -> {
//                        rv_main.visibility = View.VISIBLE
//                        progressBar.visibility = View.GONE
//                        Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
//                    }
//                    Status.LOADING -> {
//                        progressBar.visibility = View.VISIBLE
//                        rv_main.visibility = View.GONE
//                    }
//                }
//            }
//        })
    }

    private fun retrieveList(users: Call<ArrayList<NewsResult>>) {
        adapter.apply {
            addUsers(list)
            notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

