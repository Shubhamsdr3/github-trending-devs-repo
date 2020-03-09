package com.pandey.shubham.githubtrends.ui.repositories

import androidx.lifecycle.ViewModel
import com.pandey.shubham.githubtrends.data.RepositoriesDto
import com.pandey.shubham.githubtrends.model.GithubTrendRepository
import com.pandey.shubham.githubtrends.network.ApiFactory
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class RepositoriesViewModel() : ViewModel() {

    private lateinit var repositoriesViewModelListener: RepositoriesViewModelListener

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    private val repository : GithubTrendRepository = GithubTrendRepository.getInstance(ApiFactory.apiService)

    fun setListener(repositoriesViewModelListener: RepositoriesViewModelListener) {
        this.repositoriesViewModelListener = repositoriesViewModelListener
    }

    fun fetchRepositories() {
        scope.launch {
            try {
                val task= async(Dispatchers.IO) {
                    repository.fetchTrendingRepositories()
                }
                val repoResponseDto = task.await()
                repoResponseDto?.let { repositoriesViewModelListener.onFetchRepositoriesSuccess(it) }
            } catch (exception: Exception) {
                repositoriesViewModelListener.onFetchRepositoriesFailed(exception)
            }
        }
    }

    interface RepositoriesViewModelListener {

        fun onFetchRepositoriesSuccess(repositoriesList: List<RepositoriesDto>)

        fun onFetchRepositoriesFailed(throwable: Throwable)
    }
}