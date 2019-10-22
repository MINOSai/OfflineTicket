package com.minosai.repository

import android.content.SharedPreferences
import androidx.lifecycle.liveData
import com.minosai.common.Constants
import com.minosai.local.util.PreferenceHelper.set
import com.minosai.model.Result
import com.minosai.remote.auth.AuthWebClient
import kotlinx.coroutines.Dispatchers

class AuthRepository (private val webClient: AuthWebClient,
                      private val prefs: SharedPreferences) : BaseRepo() {

    fun login(creds: String) = makeRequest {
        webClient.login(creds)
    }

    fun signup(creds: String) = makeRequest {
        webClient.signup(creds)
    }

    fun setProfileType(profileType: Int) {
        prefs[Constants.PREF_PROFILE_TYPE] = profileType
    }

    fun storePhNo(phoneNumber: String) {
        prefs[Constants.PREF_PHONE_NUMBER] = phoneNumber
    }

}