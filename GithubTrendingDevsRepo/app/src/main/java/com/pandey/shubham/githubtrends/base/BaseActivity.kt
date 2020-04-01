package com.pandey.shubham.githubtrends.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.pandey.shubham.githubtrends.R
import dagger.android.support.DaggerAppCompatActivity

open class BaseActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    fun startFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.main_container, fragment, fragment::class.java.simpleName)
        if (addToBackStack) {
            transaction.addToBackStack(fragment::class.java.simpleName)
        }
        transaction.commit()
    }
}
