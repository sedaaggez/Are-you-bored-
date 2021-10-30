package com.sedaaggez.areyoubored.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sedaaggez.areyoubored.model.Activity

class ActivityViewModel(application: Application) : BaseViewModel(application){

    val activity = MutableLiveData<Activity>()
    val activityError = MutableLiveData<Boolean>()
    val activityLoading = MutableLiveData<Boolean>()

    fun getData() {
        // TODO: get data
    }

}