package com.pandey.shubham.githubtrends.ui.developers

import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pandey.shubham.githubtrends.data.DevelopersDto
import com.pandey.shubham.githubtrends.model.GithubTrendRepository
import com.pandey.shubham.githubtrends.network.ApiFactory
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DevelopersViewModel: ViewModel() {

    private lateinit var developerResponseList : MutableLiveData<List<DevelopersDto>>

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    private val repository : GithubTrendRepository = GithubTrendRepository.getInstance(ApiFactory.apiService)

    @UiThread
    fun fetchDevelopers(): LiveData<List<DevelopersDto>> {
        if (!::developerResponseList.isInitialized) {
            developerResponseList = MutableLiveData()
            scope.launch(Dispatchers.Main) {
                val developerList: List<DevelopersDto>? = async(Dispatchers.IO) {
                    return@async repository.fetchTrendingDevelopers()
                }.await()
                developerResponseList.value = developerList
            }
        }
        return developerResponseList
    }
}