package com.pandey.shubham.githubtrends.repositories.model

import com.pandey.shubham.githubtrends.GApplication
import com.pandey.shubham.githubtrends.base.BaseRepository
import com.pandey.shubham.githubtrends.network.ApiService
import com.pandey.shubham.githubtrends.network.Result
import com.pandey.shubham.githubtrends.repositories.data.RepositoriesDto
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
        Timber.d("fetching repositories from network..")
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
        Timber.d("Saving repositories to db...%s", repositoryList)
        GApplication.appDatabase.repositoryDao().insertAllRepositories(repositoryList)
//            for (repo in repositoryList) {
//                repo.contributorList?.let {
//                    GApplication.appDatabase.contributorsDao().insertAllContributors(
//                        it
//                    )
//                }
//            }
        GApplication.appDatabase.repositoryDao().getRepositoriesCount()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Timber.d("Repositories count....%s", it)
            }
    }

    fun fetchRepositoriesFromDb(): Flowable<List<RepositoriesDto>> {
        return GApplication.appDatabase.repositoryDao().getAllRepositories()
    }
}