package com.pandey.shubham.githubtrends.dagger

import com.pandey.shubham.githubtrends.HomeActivity
import com.pandey.shubham.githubtrends.HomeActivityFragmentModule
import com.pandey.shubham.githubtrends.HomeActivityModule
import com.pandey.shubham.githubtrends.ui.repositories.detail.RepoDetailsActivity
import com.pandey.shubham.githubtrends.ui.repositories.detail.RepoDetailsActivityModule
import com.pandey.shubham.githubtrends.ui.repositories.detail.RepoDetailsFragmentActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [HomeActivityModule::class, HomeActivityFragmentModule::class])
    abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [RepoDetailsActivityModule::class, RepoDetailsFragmentActivityModule::class])
    abstract fun repoDetailsActivity(): RepoDetailsActivity
}