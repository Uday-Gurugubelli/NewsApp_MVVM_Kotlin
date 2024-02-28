package com.example.newsappkotlinmvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Response

class NewsRepository(private val newsService: NewsService) {

    private val articlesLiveData  = MutableLiveData<List<Article>>()

    val articles: LiveData<List<Article>>
    get() = articlesLiveData
    suspend fun getHeadlines(country: String, page:Int) {
        val news = newsService.newsInstance.getHeadlines(country, page)
        news.enqueue(object: retrofit2.Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news:News? = response.body()
                if (news != null) {
                    articlesLiveData.postValue(news.articles)
                }
            }
            override fun onFailure(call: Call<News>, t: Throwable) {

            }
        })
    }
}