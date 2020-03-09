package com.pandey.shubham.githubtrends.ui.developers

import androidx.lifecycle.ViewModel
import com.pandey.shubham.githubtrends.data.DevelopersDto
import com.pandey.shubham.githubtrends.model.GithubTrendRepository
import com.pandey.shubham.githubtrends.network.ApiFactory
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

//FIXME: SHUBHAM
class DevelopersViewModel: ViewModel() {

    private lateinit var developersViewModelListener: DevelopersViewModelListener

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    private val repository : GithubTrendRepository = GithubTrendRepository.getInstance(ApiFactory.apiService)

    fun setListener(developersViewModelListener: DevelopersViewModelListener) {
        this.developersViewModelListener = developersViewModelListener
    }

    fun fetchDevelopers() {
        scope.launch {
            try {
                val task= async(Dispatchers.IO) {
                    repository.fetchTrendingDevelopers()
                }
                val developerResponseDto = task.await()
                developerResponseDto?.let { developersViewModelListener.onFetchDevelopersSuccess(it) }
            } catch (exception: Exception) {
                developersViewModelListener.onFetchDevelopersFailed(exception)
            }
        }
    }

    interface DevelopersViewModelListener {
        fun onFetchDevelopersSuccess(developersList: List<DevelopersDto>)

        fun onFetchDevelopersFailed(throwable: Throwable)
    }
}