package com.minosai.remote.auth

import com.minosai.remote.util.BaseApiClient

class AuthWebClient (private val service: AuthWebService) : BaseApiClient() {

    suspend fun login(creds: String) = getResult {
        service.login(creds)
    }

    suspend fun signup(creds: String) = getResult {
        service.signup(creds)
    }

}