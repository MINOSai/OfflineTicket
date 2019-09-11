package com.minosai.remote.auth

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthWebService {

    @GET("login")
    suspend fun login(@Body creds: String) : Response<String>

    @POST("signup")
    suspend fun signup(@Body creds: String) : Response<String>

}