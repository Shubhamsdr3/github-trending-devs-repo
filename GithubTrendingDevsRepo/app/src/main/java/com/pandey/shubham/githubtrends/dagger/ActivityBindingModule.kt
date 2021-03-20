package com.pandey.shubham.githubtrends.dagger

import com.pandey.shubham.githubtrends.HomeActivity
import com.pandey.shubham.githubtrends.HomeActivityFragmentModule
import com.pandey.shubham.githubtrends.HomeActivityModule
import com.pandey.shubham.githubtrends.developers.search.SearchActivity
import com.pandey.shubham.githubtrends.developers.search.SearchActivityFragmentModule
import com.pandey.shubham.githubtrends.developers.search.SearchActivityModule
import com.pandey.shubham.githubtrends.paging.NewsListActivity
import com.pandey.shubham.githubtrends.paging.NewsListActivityModule
import com.pandey.shubham.githubtrends.repositories.details.RepoDetailsActivity
import com.pandey.shubham.githubtrends.repositories.details.RepoDetailsActivityModule
import com.pandey.shubham.githubtrends.repositories.details.RepoDetailsFragmentActivityModule
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

    @ContributesAndroidInjector(modules = [NewsListActivityModule::class])
    abstract fun newsListActivity(): NewsListActivity

}