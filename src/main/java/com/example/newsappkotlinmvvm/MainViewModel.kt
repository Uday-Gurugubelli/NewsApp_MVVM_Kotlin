package com.example.newsappkotlinmvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(private val repository: NewsRepository) : ViewModel() {
    init{
        viewModelScope.launch(Dispatchers.IO) {
            repository.getHeadlines("in", 1)
        }
    }
    val articles: LiveData<List<Article>>
    get() = repository.articles
}