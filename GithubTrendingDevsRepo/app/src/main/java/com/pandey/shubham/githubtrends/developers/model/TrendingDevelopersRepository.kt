package com.pandey.shubham.githubtrends.developers.model

import androidx.lifecycle.LiveData
import com.pandey.shubham.githubtrends.GApplication
import com.pandey.shubham.githubtrends.developers.data.DevelopersDto
import com.pandey.shubham.githubtrends.base.BaseRepository
import com.pandey.shubham.githubtrends.network.ApiService
import com.pandey.shubham.githubtrends.network.Result
import timber.log.Timber

class TrendingDevelopersRepository(private val apiService: ApiService) : BaseRepository() {

    companion object {
        private var instance : TrendingDevelopersRepository? = null

        fun getInstance(apiService: ApiService?) = run {
            instance
                ?: synchronized(this) {
                    instance
                        ?: apiService?.let {
                            TrendingDevelopersRepository(
                                it
                            )
                                .also { instance = it }
                        }
                }
        }
    }

    suspend fun fetchTrendingDevelopers() {
        Timber.d("fetching developers from network..")
        val result =  makeApiCall (
            call = { apiService.getDevelopersAsync("developers").await() },
            errorMessage = "Error fetching developer details"
        )
        when(result) {
            is Result.Success -> saveToDb(result.data)
            is Result.Error -> {
                TODO()
            }
        }
    }

    private fun saveToDb(developersList: List<DevelopersDto>) {
        Timber.d("saving to db..")
//        Completable.fromAction { GApplication.appDatabase?.developersDao()?.insertAllDevelopers(developersList) } FIXME: don't use Rx java within coroutine.
        GApplication.appDatabase.developersDao().insertAllDevelopers(developersList)
    }

    fun fetchDevelopersFromDb(): LiveData<List<DevelopersDto>>? {
        return GApplication.appDatabase.developersDao().getAllDevelopers()
    }
}