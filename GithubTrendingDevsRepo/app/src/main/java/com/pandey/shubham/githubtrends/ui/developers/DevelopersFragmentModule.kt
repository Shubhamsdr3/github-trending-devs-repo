package com.pandey.shubham.githubtrends.ui.developers

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides

@Module
class DevelopersFragmentModule {

    @Provides
    fun developerViewModel(developersFragment: DevelopersFragment): DevelopersViewModel {
        return ViewModelProvider(developersFragment).get(DevelopersViewModel::class.java)
    }

}