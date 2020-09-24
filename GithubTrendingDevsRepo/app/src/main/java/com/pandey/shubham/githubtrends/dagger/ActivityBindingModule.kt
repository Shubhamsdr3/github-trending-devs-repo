package com.pandey.shubham.githubtrends.dagger

import com.pandey.shubham.githubtrends.HomeActivity
import com.pandey.shubham.githubtrends.HomeActivityFragmentModule
import com.pandey.shubham.githubtrends.HomeActivityModule
import com.pandey.shubham.githubtrends.developers.search.SearchActivity
import com.pandey.shubham.githubtrends.developers.search.SearchActivityFragmentModule
import com.pandey.shubham.githubtrends.developers.search.SearchActivityModule
import com.pandey.shubham.githubtrends.repositories.details.RepoDetailsActivity
import com.pandey.shubham.githubtrends.repositories.details.RepoDetailsActivityModule
import com.pandey.shubham.githubtrends.repositories.details.RepoDetailsFragmentActivityModule
import com.pandey.shubham.githubtrends.videoplayer.VideoPlayerActivity
import com.pandey.shubham.githubtrends.videoplayer.VideoPlayerActivityFragmentModule
import com.pandey.shubham.githubtrends.videoplayer.VideoPlayerActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [HomeActivityModule::class, HomeActivityFragmentModule::class])
    abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [RepoDetailsActivityModule::class, RepoDetailsFragmentActivityModule::class])
    abstract fun repoDetailsActivity(): RepoDetailsActivity

    @ContributesAndroidInjector(modules = [SearchActivityModule::class, SearchActivityFragmentModule::class])
    abstract fun searchActivity(): SearchActivity

    @ContributesAndroidInjector(modules = [VideoPlayerActivityModule::class, VideoPlayerActivityFragmentModule::class])
    abstract fun videoPlayerActivity(): VideoPlayerActivity

}