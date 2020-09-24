package com.pandey.shubham.githubtrends.videoplayer

import dagger.Module
import dagger.Provides

@Module
object VideoPlayerActivityModule {

    @JvmStatic
    @Provides
    fun videoPlayerActivity(videoPlayerActivity: VideoPlayerActivity): VideoPlayerActivity {
        return videoPlayerActivity
    }
}