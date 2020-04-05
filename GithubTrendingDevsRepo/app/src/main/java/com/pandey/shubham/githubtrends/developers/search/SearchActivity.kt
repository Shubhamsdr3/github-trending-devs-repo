package com.pandey.shubham.githubtrends.developers.search

import android.os.Bundle
import com.pandey.shubham.githubtrends.base.BaseActivity
import com.pandey.shubham.githubtrends.base.GlobalConstants
import com.pandey.shubham.githubtrends.developers.search.ui.SearchFragment

class SearchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //--\_"__"/--
        if (intent.hasExtra(GlobalConstants.DEVELOPER_QUERIES)) { //FIXME: SHUBHAM
            startFragment(SearchFragment.newInstance(true), false)
        } else {
            startFragment(SearchFragment.newInstance(false), false)
        }
    }
}
