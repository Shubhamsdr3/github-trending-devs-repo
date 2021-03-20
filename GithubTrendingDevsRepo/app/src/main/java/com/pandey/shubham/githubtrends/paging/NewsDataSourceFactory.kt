package com.pandey.shubham.githubtrends.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.pandey.shubham.githubtrends.network.ApiService
import io.reactivex.disposables.CompositeDisposable

class NewsDataSourceFactory(
        private val compositeDisposable: CompositeDisposable,
        private val networkService: NewsApiService)
    : DataSource.Factory<Int, News>() {

    val newsDataSourceLiveData = MutableLiveData<NewsDataSource>()

    override fun create(): DataSource<Int, News> {
        val newsDataSource = NewsDataSource(networkService, compositeDisposable)
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}