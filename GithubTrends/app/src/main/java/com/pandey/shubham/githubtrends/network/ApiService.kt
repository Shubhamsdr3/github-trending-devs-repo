package com.pandey.shubham.githubtrends.network

import com.pandey.shubham.githubtrends.data.DevelopersDto
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("{developers}")
    fun getDevelopersAsync(@Path("developers") path: String): Single<Response<List<DevelopersDto>>>
}