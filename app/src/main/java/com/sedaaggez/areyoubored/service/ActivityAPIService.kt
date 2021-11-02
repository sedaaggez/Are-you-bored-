package com.sedaaggez.areyoubored.service

import com.sedaaggez.areyoubored.model.Activity
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ActivityAPIService {

    private val BASE_URL = "http://www.boredapi.com"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ActivityAPI::class.java)

    fun getActivity() : Single<Activity> {
        return api.getActivity()
    }

}