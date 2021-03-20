package com.pandey.shubham.githubtrends.paging

import com.pandey.shubham.githubtrends.developers.search.SearchFragmentModule
import com.pandey.shubham.githubtrends.developers.search.ui.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NewsListActivityFragmentModule {

    @ContributesAndroidInjector(modules = [SearchFragmentModule::class])
    abstract fun provideSearchFragment(): SearchFragment
}
