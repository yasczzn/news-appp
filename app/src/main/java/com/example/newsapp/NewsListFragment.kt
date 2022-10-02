package com.example.newsapp

import android.graphics.ColorSpace
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class NewsListFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_list, container, false)
        Recycler_view.layoutManager= LinearLayoutManager(this
        )

        val model = ViewModelProviders.of(this).get(LiveData::class.java)
        model.getFruitList().observe(this, Observer<List<ColorSpace.Model>>{ fruitList ->
            // update UI

            data.value=fruitList
            da=fruitList!!
            Log.d("MainActivity ","Data Send"+data.value!!.size.toString())
            Recycler_view.adapter= RecyclerView.Adapter(this, data)
            progress.visibility=View.GONE
        })

    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            NewsListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}