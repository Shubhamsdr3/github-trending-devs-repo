package com.pandey.shubham.githubtrends

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pandey.shubham.githubtrends.model.HomePageAdapter
import com.pandey.shubham.githubtrends.ui.repositories.RepoDetailsActivity
import com.pandey.shubham.githubtrends.ui.repositories.RepositoriesFragment
import com.pandey.shubham.githubtrends.ui.repositories.data.RepoDetailsInfo
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() , RepositoriesFragment.RepositoriesFragmentListener {

    private lateinit var homePageAdapter: HomePageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homePageAdapter = HomePageAdapter(
            supportFragmentManager
        )
        view_pager.adapter = homePageAdapter
        tab_layout.setupWithViewPager(view_pager)
    }

    override fun onAdapterItemClicked(repoDetailsInfo: RepoDetailsInfo) {
        val intent = Intent(this, RepoDetailsActivity::class.java)
        intent.putExtra(RepositoriesFragment.REPO_DETAILS_INTENT, repoDetailsInfo)
        startActivity(intent)
    }
}
