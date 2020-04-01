package com.pandey.shubham.githubtrends.developers.search

import androidx.lifecycle.ViewModelProvider
import com.pandey.shubham.githubtrends.developers.search.model.SearchFragmentViewModel
import com.pandey.shubham.githubtrends.developers.search.ui.SearchFragment
import dagger.Module
import dagger.Provides

@Module
class SearchFragmentModule {

    @Provides
    fun searchViewModel(searchFragment: SearchFragment): SearchFragmentViewModel {
        return ViewModelProvider(searchFragment).get(SearchFragmentViewModel::class.java)
    }
}