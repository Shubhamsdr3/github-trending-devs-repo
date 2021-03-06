package com.pandey.shubham.githubtrends.repositories.details

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.pandey.shubham.githubtrends.R
import com.pandey.shubham.githubtrends.base.GlobalConstants
import com.pandey.shubham.githubtrends.repositories.details.data.RepoDetailsInfo
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity


class RepoDetailsActivity : DaggerAppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // to show fullscreen
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_repo_details)
        val repoDetailsInfo = intent.getParcelableExtra(GlobalConstants.REPO_DETAILS_INTENT) as RepoDetailsInfo
        startFragment(
            RepoDetailsFragment.newInstance(
                repoDetailsInfo
            )
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun startFragment(fragment: Fragment) {
        val transaction =  supportFragmentManager.beginTransaction()
        transaction.add(R.id.main_container_details, fragment, fragment::class.java.simpleName)
        transaction.commit()
    }
}