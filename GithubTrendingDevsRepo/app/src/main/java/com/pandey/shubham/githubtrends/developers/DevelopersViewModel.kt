package com.pandey.shubham.githubtrends.developers

import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pandey.shubham.githubtrends.GApplication
import com.pandey.shubham.githubtrends.developers.data.DevelopersDto
import com.pandey.shubham.githubtrends.developers.model.TrendingDevelopersRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DevelopersViewModel : ViewModel() {

    private val repository : TrendingDevelopersRepository? = TrendingDevelopersRepository.getInstance(GApplication.apiService)

    private val parentJob = Job() // like CompositeDisposable in Rx java
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Main // creating a local scope
    private val scope = CoroutineScope(coroutineContext)

    @UiThread
    fun makeNetworkCall() {
        scope.launch(Dispatchers.Main) { // on main thread
            withContext(Dispatchers.IO) { //on IO thread
                repository?.fetchTrendingDevelopers()
            }
        }
    }

    fun getDevelopersFromDb() = repository?.fetchDevelopersFromDb()
}