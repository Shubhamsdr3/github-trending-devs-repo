package com.pandey.shubham.githubtrends.ui.repositories.detail

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class RepoDetailsFragmentActivityModule {

    @ContributesAndroidInjector(modules = [RepoDetailsFragmentModule::class])
    abstract fun provideRepoDetailsFragment(): RepoDetailsFragment

}