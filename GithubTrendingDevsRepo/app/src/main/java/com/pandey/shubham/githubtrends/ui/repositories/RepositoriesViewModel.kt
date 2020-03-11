package com.pandey.shubham.githubtrends.ui.repositories

import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pandey.shubham.githubtrends.data.DevelopersDto
import com.pandey.shubham.githubtrends.data.RepositoriesDto
import com.pandey.shubham.githubtrends.model.GithubTrendRepository
import com.pandey.shubham.githubtrends.network.ApiFactory
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class RepositoriesViewModel() : ViewModel() {

    private lateinit var repositoriesDtoList:  MutableLiveData<List<RepositoriesDto>>

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    private val repository : GithubTrendRepository = GithubTrendRepository.getInstance(ApiFactory.apiService)

    @UiThread
    fun fetchRepositories(): LiveData<List<RepositoriesDto>> {
        if (!::repositoriesDtoList.isInitialized) {
            repositoriesDtoList = MutableLiveData()
            scope.launch(Dispatchers.Main) {
                val repositoriesList: List<RepositoriesDto>? = async(Dispatchers.IO) {
                    return@async repository.fetchTrendingRepositories()
                }.await()
                repositoriesDtoList.value = repositoriesList
            }
        }
        return repositoriesDtoList
    }
}