package com.pandey.shubham.githubtrends.developers.search

import dagger.Module
import dagger.Provides

@Module
object SearchActivityModule {

    @JvmStatic
    @Provides
    fun searchActivity(searchableActivity: SearchActivity): SearchActivity {
        return searchableActivity
    }
}