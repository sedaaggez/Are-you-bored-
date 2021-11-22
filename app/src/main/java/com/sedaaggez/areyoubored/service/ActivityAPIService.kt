package com.sedaaggez.areyoubored.service

import com.sedaaggez.areyoubored.model.Activity
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ActivityAPIService {
    private val interceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }
    val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(interceptor)

    private val BASE_URL = "http://www.boredapi.com"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient.build())
        .build()
        .create(ActivityAPI::class.java)

    fun getActivity() : Single<Activity> {
        return api.getActivity()
    }

    fun getFilterActivity(type: String, participants: Int, price: Float, accessibility: Float) : Single<Activity> {
        return api.getFilterActivity(type, participants, price, accessibility)
    }

}