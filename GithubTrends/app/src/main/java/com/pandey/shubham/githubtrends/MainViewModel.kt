package com.pandey.shubham.githubtrends

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.pandey.shubham.githubtrends.data.DevelopersDto
import com.pandey.shubham.githubtrends.network.ApiService
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel() : ViewModel() {

    val developersList : MutableLiveData<List<DevelopersDto>> = MutableLiveData()

    fun fetchDevelopers() {

    }

    private fun okHttpClient() =
        OkHttpClient()
            .newBuilder()
            .addInterceptor(ChuckInterceptor(this)) // for debug only
            .build()

    private fun apiService(): ApiService {
        val retrofit =  Retrofit.Builder()
            .client(okHttpClient())
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        return retrofit.create(ApiService::class.java)
    }


}