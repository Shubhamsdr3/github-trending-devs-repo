package com.pandey.shubham.githubtrends.network

import androidx.annotation.MainThread

abstract class NetworkResource<ResultType> {

    @MainThread
    internal constructor() {
    }
}