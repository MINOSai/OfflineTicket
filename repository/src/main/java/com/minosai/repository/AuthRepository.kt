package com.minosai.repository

import android.content.SharedPreferences
import androidx.lifecycle.liveData
import com.minosai.common.Constants
import com.minosai.model.Result
import com.minosai.remote.auth.AuthWebClient
import kotlinx.coroutines.Dispatchers
import com.minosai.local.util.PreferenceHelper.get
import com.minosai.local.util.PreferenceHelper.set

class AuthRepository (private val webClient: AuthWebClient,
                      private val prefs: SharedPreferences) {

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

    fun setProfileType(profileType: Int) {
        prefs[Constants.PREF_PROFILE_TYPE] = profileType
    }

    fun storePhNo(phoneNumber: String) {
        prefs[Constants.PREF_PHONE_NUMBER] = phoneNumber
    }

}