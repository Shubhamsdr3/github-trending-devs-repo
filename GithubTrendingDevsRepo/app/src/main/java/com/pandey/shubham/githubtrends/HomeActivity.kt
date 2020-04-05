package com.pandey.shubham.githubtrends

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.pandey.shubham.githubtrends.HomePageAdapter.Companion.FRAGMENT_INDEX_DEV
import com.pandey.shubham.githubtrends.HomePageAdapter.Companion.FRAGMENT_INDEX_REPO
import com.pandey.shubham.githubtrends.base.BaseActivity
import com.pandey.shubham.githubtrends.base.GlobalConstants
import com.pandey.shubham.githubtrends.developers.DevelopersFragment
import com.pandey.shubham.githubtrends.repositories.RepositoriesFragment
import com.pandey.shubham.githubtrends.repositories.details.RepoDetailsActivity
import com.pandey.shubham.githubtrends.repositories.details.data.RepoDetailsInfo
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_home.*
import timber.log.Timber


class HomeActivity : BaseActivity(), RepositoriesFragment.RepositoriesFragmentListener, DevelopersFragment.DeveloperFragmentListener {

    private lateinit var homePageAdapter: HomePageAdapter

    private var currentFragmentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_home)

        homePageAdapter = HomePageAdapter(supportFragmentManager)
        view_pager.adapter = homePageAdapter
        setUpInitialFragment()
        setListener()
    }

    private fun setUpInitialFragment() {
        if (currentFragmentIndex == FRAGMENT_INDEX_REPO) {
            repo_tab.setTextColor(ContextCompat.getColor(this, R.color.toolbar_color))
            dev_tab.setTextColor(ContextCompat.getColor(this, R.color.text_color_primary))
        } else if (currentFragmentIndex == FRAGMENT_INDEX_DEV) {
            dev_tab.setTextColor(ContextCompat.getColor(this, R.color.toolbar_color))
            repo_tab.setTextColor(ContextCompat.getColor(this, R.color.text_color_primary))
        }
    }

    // handles bottom navigation either by clicking tab or scrolling left/right
    private fun setListener() {
        repo_tab.setOnClickListener {
            repo_tab.setTextColor(ContextCompat.getColor(this, R.color.toolbar_color))
            dev_tab.setTextColor(ContextCompat.getColor(this, R.color.text_color_primary))
            view_pager.setCurrentItem(FRAGMENT_INDEX_REPO, false)
            currentFragmentIndex = FRAGMENT_INDEX_REPO
        }

        dev_tab.setOnClickListener {
            dev_tab.setTextColor(ContextCompat.getColor(this, R.color.toolbar_color))
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
                    repo_tab.setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.toolbar_color))
                    dev_tab.setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.text_color_primary))
                    currentFragmentIndex = FRAGMENT_INDEX_REPO
                } else if (currentSelectedFragment is DevelopersFragment) {
                    dev_tab.setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.toolbar_color))
                    repo_tab.setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.text_color_primary))
                    currentFragmentIndex = FRAGMENT_INDEX_DEV
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        return true
    }

    override fun onAdapterItemClicked(view: View, repoDetailsInfo: RepoDetailsInfo) {
        val intent = Intent(this, RepoDetailsActivity::class.java)
        intent.putExtra(GlobalConstants.REPO_DETAILS_INTENT, repoDetailsInfo)
        // adding animations
        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            androidx.core.util.Pair(
                view.findViewById(R.id.avatar_repo_image),
                getString(R.string.transition_name_image)
            ),
            androidx.core.util.Pair(
                view.findViewById(R.id.repo_author),
                getString(R.string.transition_name_repo_author)
            ),

            androidx.core.util.Pair(
                view.findViewById(R.id.repo_name),
                getString(R.string.transition_name_repo_name)
            ),
            androidx.core.util.Pair(
                view.findViewById(R.id.repo_descriptions),
                getString(R.string.transition_name_description)
            ),
            androidx.core.util.Pair(
                view.findViewById(R.id.repo_stars_icon),
                getString(R.string.transition_name_stars)
            ),

            androidx.core.util.Pair(
                view.findViewById(R.id.repo_fork_icon),
                getString(R.string.transition_name_forks)
            )
        )
        startActivity(intent, activityOptions.toBundle())
    }

    override fun onSearchClicked() {
        Timber.d("On Search icon clicked.....")
    }
}
