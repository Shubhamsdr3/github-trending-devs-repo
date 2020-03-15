package com.pandey.shubham.githubtrends.ui.repositories

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides

@Module
class RepositoryFragmentModule {

     @Provides
     fun repositoryViewModel(fragment: RepositoriesFragment): RepositoriesViewModel {
         return ViewModelProvider(fragment).get(RepositoriesViewModel::class.java)
     }
}