package com.pandey.shubham.githubtrends.model

import com.pandey.shubham.githubtrends.data.DevelopersDto
import com.pandey.shubham.githubtrends.data.RepositoriesDto
import com.pandey.shubham.githubtrends.network.ApiService

class GithubTrendRepository private constructor(private val apiService: ApiService) : BaseRepository() {

    suspend fun fetchTrendingDevelopers() : List<DevelopersDto>? {
        return safeApiCall (
            call = { apiService.getDevelopersAsync("developers").await() },
            errorMessage = "Error Fetching Flights"
        )
    }

    suspend fun fetchTrendingRepositories() : List<RepositoriesDto>? {
        return safeApiCall (
            call = { apiService.getRepositoriesAsync("repositories").await() },
            errorMessage = "Error Fetching Flights"
        )
    }

    companion object {

        @Volatile private var instance : GithubTrendRepository? = null

        fun getInstance(apiService: ApiService) = run {
            instance
                ?: synchronized(this) {
                instance
                    ?: GithubTrendRepository(
                        apiService
                    ).also { instance = it }
            }
        }
    }
}