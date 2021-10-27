package com.sedaaggez.areyoubored.service

import com.sedaaggez.areyoubored.model.Activity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ActivityAPI {

    @GET("/api/activity/")
    fun getActivity(): Single<Activity>

}