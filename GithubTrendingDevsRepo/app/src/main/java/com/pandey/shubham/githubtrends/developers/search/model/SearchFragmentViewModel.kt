package com.pandey.shubham.githubtrends.developers.search.model

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.pandey.shubham.githubtrends.GApplication
import com.pandey.shubham.githubtrends.base.BaseViewModel
import com.pandey.shubham.githubtrends.developers.data.DevelopersDto
import com.pandey.shubham.githubtrends.repositories.data.RepositoriesDto
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SearchFragmentViewModel : BaseViewModel() {

    private val searchedDevelopers = MediatorLiveData<List<DevelopersDto>>()

    private val searchedRepositories = MediatorLiveData<List<RepositoriesDto>>()

    private val developerQuerySubject = PublishSubject.create<String>()

    private val repositoryQuerySubject = PublishSubject.create<String>()

    val developersLiveData : LiveData<List<DevelopersDto>>
        get() = searchedDevelopers

    val repositoriesLiveData : LiveData<List<RepositoriesDto>>
        get() = searchedRepositories

    init {
        developerQuerySubject.debounce(300, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                if (!TextUtils.isEmpty(it)) {
                    makeDevelopersDbCall("%$it%")
                }
            }
            .subscribe()

        repositoryQuerySubject.debounce(300, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                if (!TextUtils.isEmpty(it)) {
                    makeRepositoryDbCall("%$it%")
                }
            }
            .subscribe()
    }

    fun onDevelopersQuerySearch(text: String) {
        developerQuerySubject.onNext(text)
    }

    fun onRepositoryQuerySearch(text: String) {
        repositoryQuerySubject.onNext(text)
    }

    private fun makeDevelopersDbCall(text: String) {
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

    private fun makeRepositoryDbCall(text: String) {
        Timber.d("Making db call.....")
        addDisposable(
            GApplication.appDatabase
                .repositoryDao()
                .getRepositories(text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Timber.d("fetched developers....%s", it.size)
                    searchedRepositories.value = it
                }
        )
    }

}