package com.example.newsappkotlinmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsappkotlinmvvm.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response
import java.util.Stack
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var adapter : NewsAdapter
    lateinit var binding : ActivityMainBinding
    private var articles = mutableListOf<Article>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newsService : NewsService = NewsService
        val repository = NewsRepository(newsService)

        //binding = DataBindingUtil.setContentView(this, R.layout.item_view)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = NewsAdapter(this@MainActivity, articles)
        //val newsList: RecyclerView = findViewById(R.id.newsList)
        binding.newsList.adapter = adapter
        binding.newsList.layoutManager = LinearLayoutManager(this@MainActivity)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.articles.observe(this, Observer {
            articles.addAll(it.toMutableList())
            adapter.notifyDataSetChanged()
        } )
    }
}
