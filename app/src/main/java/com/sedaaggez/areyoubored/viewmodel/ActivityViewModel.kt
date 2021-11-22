package com.sedaaggez.areyoubored.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sedaaggez.areyoubored.model.Activity
import com.sedaaggez.areyoubored.service.ActivityAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ActivityViewModel(application: Application) : BaseViewModel(application){

    val activity = MutableLiveData<Activity>()
    val activityError = MutableLiveData<Boolean>()
    val activityLoading = MutableLiveData<Boolean>()

    private val activityAPIService = ActivityAPIService()
    private val disposable = CompositeDisposable()

    fun getDataRandom() {
        activityError.value = false
        disposable.add(
            activityAPIService.getActivity()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Activity>() {
                    override fun onSuccess(t: Activity) {
                        activity.value = t
                        activityError.value = false
                        activityLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        activityError.value = true
                        activityLoading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }

    fun getDataFilter(type: String, participants: Int, price: Float, accessibility: Float) {
        activityError.value = false
        disposable.add(
            activityAPIService.getFilterActivity(type, participants, price, accessibility)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Activity>() {
                    override fun onSuccess(t: Activity) {
                        activity.value = t
                        activityError.value = false
                        activityLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        activityError.value = true
                        activityLoading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}