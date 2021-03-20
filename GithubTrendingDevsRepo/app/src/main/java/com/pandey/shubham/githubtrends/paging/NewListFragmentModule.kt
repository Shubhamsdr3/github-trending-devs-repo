package com.pandey.shubham.githubtrends.paging

import androidx.lifecycle.ViewModelProvider
import com.pandey.shubham.githubtrends.developers.search.model.SearchFragmentViewModel
import dagger.Module
import dagger.Provides

@Module
class NewListFragmentModule {

    @Provides
    fun newsListViewModel(newListFragment: NewsListFragment): SearchFragmentViewModel {
        return ViewModelProvider(newListFragment).get(SearchFragmentViewModel::class.java)
    }
}