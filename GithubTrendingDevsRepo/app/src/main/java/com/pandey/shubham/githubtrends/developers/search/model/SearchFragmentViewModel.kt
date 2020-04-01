package com.pandey.shubham.githubtrends.developers.search.model

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.pandey.shubham.githubtrends.GApplication
import com.pandey.shubham.githubtrends.base.BaseViewModel
import com.pandey.shubham.githubtrends.developers.data.DevelopersDto
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SearchFragmentViewModel : BaseViewModel() {

    private val searchedDevelopers = MediatorLiveData<List<DevelopersDto>>()

    private val subject = PublishSubject.create<String>()

    val developersLiveData : LiveData<List<DevelopersDto>>
        get() = searchedDevelopers

    init {
        subject.debounce(300, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                if (!TextUtils.isEmpty(it)) {
                    makeDbCall("%$it%")
                }
            }
            .subscribe()
    }

    fun onQuerySearch(text: String) {
        subject.onNext(text)
    }

    private fun makeDbCall(text: String) {
        Timber.d("Making db call.....")
        addDisposable(
        GApplication.appDatabase
            .developersDao()
            .getDevelopers(text)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Timber.d("fetched developers....%s", it.size)
                searchedDevelopers.value = it
            }
        )
    }

}