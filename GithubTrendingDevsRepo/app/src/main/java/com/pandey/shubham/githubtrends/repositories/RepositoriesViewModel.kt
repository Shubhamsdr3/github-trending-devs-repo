package com.pandey.shubham.githubtrends.repositories

import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import com.pandey.shubham.githubtrends.GApplication
import com.pandey.shubham.githubtrends.base.BaseViewModel
import com.pandey.shubham.githubtrends.repositories.data.RepositoriesDto
import com.pandey.shubham.githubtrends.repositories.model.TrendingReposRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class RepositoriesViewModel : BaseViewModel() {

    private val repository : TrendingReposRepository = TrendingReposRepository.getInstance(GApplication.apiService)

    private val parentJob = Job() // like CompositeDisposable in Rx java
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Main // creating a local scope
    private val scope = CoroutineScope(coroutineContext)

    @UiThread
    fun fetchRepositoriesFromNetwork() {
        scope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                repository.fetchTrendingRepositories()
            }
        }
    }

    fun getRepositoriesFromDb(): LiveData<List<RepositoriesDto>> {
        return repository.fetchRepositoriesFromDb()
    }
}