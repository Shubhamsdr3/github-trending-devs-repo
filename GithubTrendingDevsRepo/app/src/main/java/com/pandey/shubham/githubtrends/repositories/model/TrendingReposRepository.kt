package com.pandey.shubham.githubtrends.repositories.model

import androidx.lifecycle.LiveData
import com.pandey.shubham.githubtrends.GApplication
import com.pandey.shubham.githubtrends.base.BaseRepository
import com.pandey.shubham.githubtrends.network.ApiService
import com.pandey.shubham.githubtrends.network.Result
import com.pandey.shubham.githubtrends.repositories.data.RepositoriesDto
import timber.log.Timber

class TrendingReposRepository private constructor(private val apiService: ApiService) : BaseRepository() {

    companion object {
        const val REPOSITORY_PATH_PARAM = "repositories"

        @Volatile private var instance : TrendingReposRepository? = null

        fun getInstance(apiService: ApiService) = run {
            instance
                ?: synchronized(this) {
                instance
                    ?: apiService.let { it ->
                        TrendingReposRepository(
                            it
                        ).also { instance = it }
                    }
            }
        }
    }

    suspend fun fetchTrendingRepositories() {
        val result =  makeApiCall (
            call = { apiService.getRepositoriesAsync(REPOSITORY_PATH_PARAM).await() },
            errorMessage = "Error fetching developer details"
        )
        when(result) {
            is Result.Success -> saveRepositoriesToDb(result.data)
            is Result.Error -> {
                TODO()
            }
        }
    }

    private fun saveRepositoriesToDb(repositoryList: List<RepositoriesDto>) {
        Timber.d("inserting data in ")
        GApplication.appDatabase.repositoryDao().insertAllRepositories(repositoryList)
//            for (repo in repositoryList) {
//                repo.contributorList?.let {
//                    GApplication.appDatabase.contributorsDao().insertAllContributors(
//                        it
//                    )
//                }
//            }
    }

    fun fetchRepositoriesFromDb(): LiveData<List<RepositoriesDto>> {
        return GApplication.appDatabase.repositoryDao().getAllRepositories()
    }
}