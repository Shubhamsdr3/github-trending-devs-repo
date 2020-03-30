package com.pandey.shubham.githubtrends.repositories.details

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class RepoDetailsFragmentActivityModule {

    @ContributesAndroidInjector(modules = [RepoDetailsFragmentModule::class])
    abstract fun provideRepoDetailsFragment(): RepoDetailsFragment

}