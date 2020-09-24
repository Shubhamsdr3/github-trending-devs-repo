package com.pandey.shubham.githubtrends.videoplayer

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class VideoPlayerActivityFragmentModule {

    @ContributesAndroidInjector
    abstract fun provideVideoPlayerFragment(): VideoPlayerFragment
}
