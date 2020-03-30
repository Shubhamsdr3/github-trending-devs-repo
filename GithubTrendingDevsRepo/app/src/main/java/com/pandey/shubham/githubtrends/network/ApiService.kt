package com.pandey.shubham.githubtrends.network

import com.pandey.shubham.githubtrends.developers.data.DevelopersDto
import com.pandey.shubham.githubtrends.repositories.data.RepositoriesDto
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    /**
     * Handle the response with kotlin coroutine
     * @return: Deferred<T>
     */
    @GET("{developers}")
    fun getDevelopersAsync(@Path("developers") path: String): Deferred<Response<List<DevelopersDto>>>

    /**
     * Handle the response with RxJava
     * @return: Single<T>
     */
    @GET("{repositories}")
    fun getRepositoriesAsync(@Path("repositories") repo: String): Deferred<Response<List<RepositoriesDto>>>
}