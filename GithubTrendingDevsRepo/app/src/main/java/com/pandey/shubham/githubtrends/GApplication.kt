package com.pandey.shubham.githubtrends

import android.app.Activity
import android.app.Application
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.pandey.shubham.githubtrends.dagger.DaggerAppComponent
import com.pandey.shubham.githubtrends.db.AppDatabase
import com.pandey.shubham.githubtrends.network.ApiService
import com.pandey.shubham.githubtrends.network.AuthInterceptor
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

class GApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

    companion object {
        const val APP_DB_NAME = "github_trend"
        lateinit var appDatabase : AppDatabase
        lateinit var apiService: ApiService
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this)?.build()?.inject(this)
        Timber.plant(DebugTree())
        //initialize room db
        appDatabase =
            Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java,
                    APP_DB_NAME
                )
                .fallbackToDestructiveMigration() // when we don't provide migration
                .build()
        apiService = apiService()
    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return activityInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<androidx.fragment.app.Fragment>? {
        return fragmentInjector
    }

    private fun okHttpClient() =
        OkHttpClient()
            .newBuilder()
            .addInterceptor(ChuckInterceptor(this)) // for debug only
            .addInterceptor(AuthInterceptor())
            .build()

    private fun apiService(): ApiService {
       val retrofit =  Retrofit.Builder()
           .client(okHttpClient())
           .baseUrl(BuildConfig.BASE_URL)
           .addConverterFactory(GsonConverterFactory.create())
           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
           .addCallAdapterFactory(CoroutineCallAdapterFactory())
           .build()
        return retrofit.create(ApiService::class.java)
    }

}