package com.pandey.shubham.githubtrends.ui.repositories

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.pandey.shubham.githubtrends.R
import com.pandey.shubham.githubtrends.ui.repositories.RepositoriesFragment.Companion.REPO_DETAILS_INTENT
import com.pandey.shubham.githubtrends.ui.repositories.data.RepoDetailsInfo

class RepoDetailsActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_details)
        val repoDetailsInfo = intent.getParcelableExtra(REPO_DETAILS_INTENT) as RepoDetailsInfo
        startFragment(RepoDetailsFragment.newInstance(repoDetailsInfo))
    }

    private fun startFragment(fragment: Fragment) {
        val transaction =  supportFragmentManager.beginTransaction()
        transaction.add(R.id.main_container, fragment, RepoDetailsFragment::class.java.simpleName)
        transaction.commit()
    }
}