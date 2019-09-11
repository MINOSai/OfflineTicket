package com.minosai.repository

import androidx.lifecycle.liveData
import com.minosai.model.Result
import com.minosai.remote.auth.AuthWebClient
import kotlinx.coroutines.Dispatchers

class AuthRepository (private val webClient: AuthWebClient) {

    fun login(creds: String) = liveData(Dispatchers.IO) {
        emit(Result.loading())

        val response = webClient.login(creds)

        when (response.status) {
            Result.Status.SUCCESS -> {
                Result.success("Login successful")
            }
            Result.Status.ERROR -> {
                emit(Result.error<String>(response.message!!))
            }
            else -> {

            }
        }
    }

    fun signup(creds: String) = liveData(Dispatchers.IO) {
        emit(Result.loading())

        val response = webClient.signup(creds)

        when (response.status) {
            Result.Status.SUCCESS -> {
                Result.success("Login successful")
            }
            Result.Status.ERROR -> {
                emit(Result.error<String>(response.message!!))
            }
            else -> {

            }
        }
    }

}