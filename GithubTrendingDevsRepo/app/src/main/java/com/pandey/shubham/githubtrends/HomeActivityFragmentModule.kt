package com.pandey.shubham.githubtrends

import com.pandey.shubham.githubtrends.developers.DevelopersFragment
import com.pandey.shubham.githubtrends.developers.DevelopersFragmentModule
import com.pandey.shubham.githubtrends.repositories.RepositoriesFragment
import com.pandey.shubham.githubtrends.repositories.RepositoryFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeActivityFragmentModule {

    @ContributesAndroidInjector(modules = [DevelopersFragmentModule::class])
    abstract fun provideDeveloperFragment(): DevelopersFragment

    @ContributesAndroidInjector(modules = [RepositoryFragmentModule::class])
    abstract fun provideRepositoryFragment(): RepositoriesFragment

}