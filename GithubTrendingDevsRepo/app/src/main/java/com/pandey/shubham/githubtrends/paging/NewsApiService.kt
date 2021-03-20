package com.pandey.shubham.githubtrends.paging

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("/v2/everything?q=sports&apiKey=aa67d8d98c8e4ad1b4f16dbd5f3be348")
    fun fetchNews(@Query("page") page: Int, @Query("pageSize") pageSize: Int): Single<Response>

}