package com.example.projectdenver.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectdeltatwo.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var newsAdapter: NewsAdapter
    private val viewModel by viewModels<NewsViewModel>()
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsAdapter = NewsAdapter()
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = newsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.setHasFixedSize(true)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.newsList.collect{article->
                    newsAdapter.setNews(article)
                }
            }
        }
    }
}