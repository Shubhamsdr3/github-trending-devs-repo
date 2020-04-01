package com.pandey.shubham.githubtrends.network

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RxAndroidExample {

    private val observable : Observable<Array<Int>> = Observable.fromArray(arrayOf(1, 2, 3, 4, 5))

    fun function() {
        observable.observeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Array<Int>> {

                override fun onComplete() {
                    TODO("Not yet implemented")
                }

                override fun onSubscribe(d: Disposable) {
                    TODO("Not yet implemented")
                }

                override fun onNext(t: Array<Int>) {
                    TODO("Not yet implemented")
                }

                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

    fun main() {

    }
}