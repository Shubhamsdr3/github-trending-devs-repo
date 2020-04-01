package com.pandey.shubham.githubtrends.developers.search

import android.os.Bundle
import com.pandey.shubham.githubtrends.base.BaseActivity
import com.pandey.shubham.githubtrends.developers.search.ui.SearchFragment

class SearchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startFragment(SearchFragment.newInstance(), false)
    }
}
