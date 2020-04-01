package com.pandey.shubham.githubtrends.developers.search

import com.pandey.shubham.githubtrends.developers.search.ui.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchActivityFragmentModule {

    @ContributesAndroidInjector(modules = [SearchFragmentModule::class])
    abstract fun provideSearchFragment(): SearchFragment
}
