package com.pandey.shubham.githubtrends.paging

import dagger.Module
import dagger.Provides

@Module
object NewsListActivityModule {

    @JvmStatic
    @Provides
    fun getNewListActivity(newsActivity: NewsListActivity): NewsListActivity {
        return newsActivity
    }
}