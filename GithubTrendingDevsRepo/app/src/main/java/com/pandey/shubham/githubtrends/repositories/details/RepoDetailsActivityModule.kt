package com.pandey.shubham.githubtrends.repositories.details

import com.pandey.shubham.githubtrends.repositories.details.RepoDetailsActivity
import dagger.Module
import dagger.Provides

@Module
object RepoDetailsActivityModule {

    @JvmStatic
    @Provides
    fun repoDetailsActivity(repoDetailsActivity: RepoDetailsActivity): RepoDetailsActivity {
        return repoDetailsActivity
    }
}