package com.pandey.shubham.githubtrends.network

import com.pandey.shubham.githubtrends.data.DevelopersDto
import com.pandey.shubham.githubtrends.data.RepositoriesDto
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("{developers}")
    fun getDevelopersAsync(@Path("developers") path: String): Deferred<Response<List<DevelopersDto>>>

    @GET("{repositories}")
    fun getRepositoriesAsync(@Path("repositories") repo: String): Deferred<Response<List<RepositoriesDto>>>
}