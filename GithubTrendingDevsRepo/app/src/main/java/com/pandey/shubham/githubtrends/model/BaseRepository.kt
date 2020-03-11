package com.pandey.shubham.githubtrends.model

import android.annotation.SuppressLint
import com.pandey.shubham.githubtrends.network.Result
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

open class BaseRepository {

    @SuppressLint("TimberArgCount")
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {

        val result : Result<T> = safeApiResult(call,errorMessage)
        var data : T? = null
        when(result) {
            is Result.Success ->
                data = result.data
            is Result.Error -> {
                Timber.d("1.DataRepository", "$errorMessage & Exception - ${result.exception}")
            }
        }
        return data
    }

    private suspend fun <T: Any> safeApiResult(call: suspend ()-> Response<T>, errorMessage: String) : Result<T> {
        val response = call.invoke()
        if(response.isSuccessful) {
            return Result.Success(response.body()!!)
        }

        return Result.Error(IOException("Error Occurred - $errorMessage"))
    }
}