package com.pandey.shubham.githubtrends.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Provide header uid, at for authentication purpose
 */
class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newUrl = chain.request().url()
            .newBuilder()
            .build()

        val newRequest = chain.request()
            .newBuilder()
//            .header()
            .url(newUrl)
            .build()
       return chain.proceed(newRequest)
    }
}