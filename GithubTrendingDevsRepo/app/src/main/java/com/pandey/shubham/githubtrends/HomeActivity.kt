package com.pandey.shubham.githubtrends

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.pandey.shubham.githubtrends.model.HomePageAdapter
import com.pandey.shubham.githubtrends.ui.developers.DevelopersFragment
import com.pandey.shubham.githubtrends.ui.repositories.RepoDetailsActivity
import com.pandey.shubham.githubtrends.ui.repositories.RepositoriesFragment
import com.pandey.shubham.githubtrends.ui.repositories.data.RepoDetailsInfo
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() , RepositoriesFragment.RepositoriesFragmentListener {

    private lateinit var homePageAdapter: HomePageAdapter

    private var searchView : SearchView ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homePageAdapter = HomePageAdapter(supportFragmentManager)
        view_pager.adapter = homePageAdapter
        tab_layout.setupWithViewPager(view_pager)

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                searchView?.setQuery("", false)
                searchView?.clearFocus()
                val fragment = homePageAdapter.getFragment(tab_layout.selectedTabPosition)
                changeSearchHint(fragment)
            }
        })
    }

    override fun onAdapterItemClicked(repoDetailsInfo: RepoDetailsInfo) {
        val intent = Intent(this, RepoDetailsActivity::class.java)
        intent.putExtra(RepositoriesFragment.REPO_DETAILS_INTENT, repoDetailsInfo)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.repo_search_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        searchView = item.actionView as SearchView
        val fragment = homePageAdapter.getFragment(tab_layout.selectedTabPosition)
        changeSearchHint(fragment)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (fragment is RepositoriesFragment) {
                    newText?.let { fragment.onRepositorySearch(it) }
                } else if (fragment is DevelopersFragment) {
                    newText?.let { fragment.onDeveloperSearch(it) }
                }
                return false
            }
        })

        return super.onOptionsItemSelected(item)
    }

    private fun changeSearchHint(fragment: Fragment?) {
        if (fragment is RepositoriesFragment) {
            searchView?.queryHint = getString(R.string.search_repo)
        } else {
            searchView?.queryHint = getString(R.string.search_devs)
        }
    }
}
