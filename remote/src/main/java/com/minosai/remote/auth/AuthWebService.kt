package com.minosai.remote.auth

import com.minosai.model.auth.AuthRequest
import com.minosai.model.auth.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthWebService {

    @POST("auth/token/")
    suspend fun login(@Body loginRequest: AuthRequest): Response<AuthResponse>

    @POST("auth/users/")
    suspend fun signup(@Body signupRequest: AuthRequest): Response<AuthResponse>

}