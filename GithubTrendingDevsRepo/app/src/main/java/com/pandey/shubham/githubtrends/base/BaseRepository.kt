package com.pandey.shubham.githubtrends.base

import com.pandey.shubham.githubtrends.network.Result
import retrofit2.Response
import java.io.IOException

open class BaseRepository {

    suspend fun <T : Any> makeApiCall(call: suspend () -> Response<T>, errorMessage: String): Result<T> {
        val result : Result<T>

        val response = call.invoke()

        result = if (response.isSuccessful) {
            Result.Success(response.body()!!)
        } else {
            Result.Error(IOException(errorMessage))
        }
        return result
    }

}