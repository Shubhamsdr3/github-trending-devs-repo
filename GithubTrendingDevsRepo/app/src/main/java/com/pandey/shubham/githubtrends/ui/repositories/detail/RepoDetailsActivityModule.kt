package com.pandey.shubham.githubtrends.ui.repositories.detail

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