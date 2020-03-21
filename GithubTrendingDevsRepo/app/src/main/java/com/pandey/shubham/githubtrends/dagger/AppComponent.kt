package com.pandey.shubham.githubtrends.dagger

import android.app.Application
import com.pandey.shubham.githubtrends.GApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ActivityBindingModule::class])
interface AppComponent : AndroidInjector<GApplication> {

    override fun inject(instance: GApplication?)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application?): Builder?
        fun build(): AppComponent?
    }
}