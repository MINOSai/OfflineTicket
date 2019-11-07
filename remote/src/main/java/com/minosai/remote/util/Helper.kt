package com.minosai.remote.util

import android.content.SharedPreferences
import com.minosai.common.Constants
import com.minosai.remote.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

fun getTokenInterceptor(prefs: SharedPreferences): OkHttpClient {

    val httpClient = OkHttpClient.Builder()

    httpClient.addInterceptor { chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .addHeader(
                "Authorization",
                "Bearer ${prefs.getString(Constants.PREF_ACCESS_TOKEN, "") ?: ""}"
            )
        val request = requestBuilder.build()
        return@addInterceptor chain.proceed(request)
    }

    if (BuildConfig.DEBUG) {
        httpClient.addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        )
    }

    return httpClient.build()
}

fun getLoggingInterceptor(): OkHttpClient {
    val httpClient = OkHttpClient.Builder()

    if (BuildConfig.DEBUG) {
        httpClient.addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        )
    }

    return httpClient.build()
}