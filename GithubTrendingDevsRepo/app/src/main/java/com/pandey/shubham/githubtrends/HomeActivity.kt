package com.pandey.shubham.githubtrends

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.pandey.shubham.githubtrends.model.HomePageAdapter
import com.pandey.shubham.githubtrends.model.HomePageAdapter.Companion.FRAGMENT_INDEX_DEV
import com.pandey.shubham.githubtrends.model.HomePageAdapter.Companion.FRAGMENT_INDEX_REPO
import com.pandey.shubham.githubtrends.ui.developers.DevelopersFragment
import com.pandey.shubham.githubtrends.ui.repositories.RepoDetailsActivity
import com.pandey.shubham.githubtrends.ui.repositories.RepositoriesFragment
import com.pandey.shubham.githubtrends.ui.repositories.data.RepoDetailsInfo
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() , RepositoriesFragment.RepositoriesFragmentListener {

    private lateinit var homePageAdapter: HomePageAdapter

    private var searchView : SearchView ? = null

    private var currentFragmentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homePageAdapter = HomePageAdapter(supportFragmentManager)
        view_pager.adapter = homePageAdapter
        setUpInitialFragment()
        setListener()
    }

    private fun setUpInitialFragment() {
        if (currentFragmentIndex == FRAGMENT_INDEX_REPO) {
            repo_tab.setTextColor(ContextCompat.getColor(this, R.color.bottom_color))
            dev_tab.setTextColor(ContextCompat.getColor(this, R.color.text_color_primary))
        } else if (currentFragmentIndex == FRAGMENT_INDEX_DEV) {
            dev_tab.setTextColor(ContextCompat.getColor(this, R.color.bottom_color))
            repo_tab.setTextColor(ContextCompat.getColor(this, R.color.text_color_primary))
        }
    }

    // handles bottom navigation either by clicking tab or scrolling left/right
    private fun setListener() {
        repo_tab.setOnClickListener {
            repo_tab.setTextColor(ContextCompat.getColor(this, R.color.bottom_color))
            dev_tab.setTextColor(ContextCompat.getColor(this, R.color.text_color_primary))
            view_pager.setCurrentItem(FRAGMENT_INDEX_REPO, false)
            currentFragmentIndex = FRAGMENT_INDEX_REPO
        }

        dev_tab.setOnClickListener {
            dev_tab.setTextColor(ContextCompat.getColor(this, R.color.bottom_color))
            repo_tab.setTextColor(ContextCompat.getColor(this, R.color.text_color_primary))
            view_pager.setCurrentItem(FRAGMENT_INDEX_DEV, false)
            currentFragmentIndex = FRAGMENT_INDEX_DEV
        }

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                val currentSelectedFragment = homePageAdapter.getItem(position)
                if (currentSelectedFragment is RepositoriesFragment) {
                    repo_tab.setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.green))
                    dev_tab.setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.text_color_primary))
                    currentFragmentIndex = FRAGMENT_INDEX_REPO
                } else if (currentSelectedFragment is DevelopersFragment) {
                    dev_tab.setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.green))
                    repo_tab.setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.text_color_primary))
                    currentFragmentIndex = FRAGMENT_INDEX_DEV
                }
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

    private fun changeSearchHint(fragment: Fragment?) {
        if (fragment is RepositoriesFragment) {
            searchView?.queryHint = getString(R.string.search_repo)
        } else {
            searchView?.queryHint = getString(R.string.search_devs)
        }
    }
}