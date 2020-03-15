package com.pandey.shubham.githubtrends

import android.app.Activity
import android.app.Application
import com.pandey.shubham.githubtrends.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class GApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this)?.build()?.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return activityInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<androidx.fragment.app.Fragment>? {
        return fragmentInjector
    }

}