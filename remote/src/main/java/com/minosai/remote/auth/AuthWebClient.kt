package com.minosai.remote.auth

import com.minosai.model.auth.AuthRequest
import com.minosai.remote.util.BaseApiClient

class AuthWebClient (private val service: AuthWebService) : BaseApiClient() {

    suspend fun login(loginRequest: AuthRequest) = getResult {
        service.login(loginRequest)
    }

    suspend fun signup(signupRequest: AuthRequest) = getResult {
        service.signup(signupRequest)
    }

}