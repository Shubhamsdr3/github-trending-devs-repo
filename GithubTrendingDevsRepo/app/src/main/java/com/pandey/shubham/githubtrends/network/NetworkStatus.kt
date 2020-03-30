package com.pandey.shubham.githubtrends.network

sealed class NetworkStatus {

    object SUCCESS : NetworkStatus()

    object LOADING : NetworkStatus()

    object ERROR : NetworkStatus()

}