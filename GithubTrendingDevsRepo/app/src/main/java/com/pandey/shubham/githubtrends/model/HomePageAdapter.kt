package com.pandey.shubham.githubtrends.model

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.pandey.shubham.githubtrends.ui.developers.DevelopersFragment
import com.pandey.shubham.githubtrends.ui.repositories.RepositoriesFragment

class HomePageAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        const val FRAGMENT_INDEX_REPO = 0
        const val NUM_OF_ITEMS = 2
        const val FRAGMENT_DEV_TITLE = "Developers"
        const val FRAGMENT_REPO_TITLE = "Repositories"
    }

    override fun getItem(position: Int): Fragment {
        return if (position == FRAGMENT_INDEX_REPO) {
            RepositoriesFragment.newInstance()
        } else {
            DevelopersFragment.newInstance()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == FRAGMENT_INDEX_REPO) {
            FRAGMENT_REPO_TITLE
        } else {
            FRAGMENT_DEV_TITLE
        }
    }

    override fun getCount(): Int {
        return NUM_OF_ITEMS
    }
}