package com.pandey.shubham.githubtrends.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NetworkBroadCastReceiver : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {
        if (ConnectionUtils.isInternetAvailable(p0)) {

        }
    }
}