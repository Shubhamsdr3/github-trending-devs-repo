package com.pandey.shubham.githubtrends

import dagger.Module
import dagger.Provides

@Module
object HomeActivityModule {

    @JvmStatic
    @Provides
    fun getHomeActivity(homeActivity: HomeActivity): HomeActivity {
        return homeActivity
    }

}