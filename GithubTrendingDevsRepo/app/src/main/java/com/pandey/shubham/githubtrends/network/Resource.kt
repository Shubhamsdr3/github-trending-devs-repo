package com.pandey.shubham.githubtrends.network

data class Resource<out T>(val status: NetworkStatus, val data: T?, val errorMessage: String) {

    companion object {
        fun <T>  success(data: T?): Resource<T> {
            return Resource(NetworkStatus.SUCCESS, data, "")
        }

        fun <T>  loading(data: T?): Resource<T> {
            return Resource(NetworkStatus.LOADING, data, "")
        }

        fun <T>  error(data: T?): Resource<T> {
            return Resource(NetworkStatus.ERROR, data, "")
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other){
            return true
        }
        if (javaClass != other?.javaClass){
            return false
        }

        other as Resource<*>

        if (status != other.status && data != other.data
            && errorMessage != other.errorMessage ){
            return false
        }
        
        return true
    }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 31 * result + (data?.hashCode() ?: 0)
        result = 31 * result + errorMessage.hashCode()
        return result
    }


}