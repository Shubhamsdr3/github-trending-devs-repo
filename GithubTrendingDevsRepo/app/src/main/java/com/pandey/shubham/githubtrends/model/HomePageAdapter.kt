package com.pandey.shubham.githubtrends.model

import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.pandey.shubham.githubtrends.ui.developers.DevelopersFragment
import com.pandey.shubham.githubtrends.ui.repositories.RepositoriesFragment
import java.lang.ref.WeakReference

/**
 * Handles bottom navigation either by clicking on tab or scrolling left/right
 */
class HomePageAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val instantiatedFragments: SparseArray<WeakReference<Fragment>> = SparseArray()

    companion object {
        const val FRAGMENT_INDEX_REPO = 0
        const val FRAGMENT_INDEX_DEV = 1
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

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment
        instantiatedFragments.put(
            position,
            WeakReference(fragment)
        )
        return fragment
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        instantiatedFragments.remove(position)
        super.destroyItem(container, position, obj)
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

    fun getFragment(position: Int): Fragment? {
        val wr = instantiatedFragments[position]
        return wr?.get()
    }
}