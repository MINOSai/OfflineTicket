package com.minosai.repository

import android.content.SharedPreferences
import com.minosai.common.Constants
import com.minosai.local.util.PreferenceHelper.set
import com.minosai.model.auth.AuthRequest
import com.minosai.remote.auth.AuthWebClient
import com.minosai.repository.util.BaseRepo

class AuthRepository(
    private val webClient: AuthWebClient,
    private val prefs: SharedPreferences
) : BaseRepo() {

    fun login(userName: String, password: String, profileType: Int) = makeRequest(
        request = {
            webClient.login(AuthRequest(userName, password, profileType))
        },
        onSuccess = { response ->
            response?.let {
                storeAccessToken(it.access)
                storeRefreshToken(it.refresh)
                storeProfileType(profileType)
                storeUserId(it.id)
            }
        }
    )

    fun signup(userName: String, password: String, profileType: Int) = makeRequest {
        webClient.signup(AuthRequest(userName, password, profileType))
    }

    private fun storeRefreshToken(refresh: String) {
        prefs[Constants.PREF_REFRESH_TOKEN] = refresh
    }

    private fun storeAccessToken(access: String) {
        prefs[Constants.PREF_ACCESS_TOKEN] = access
    }

    private fun storeUserId(id: Int) {
        prefs[Constants.PREF_USER_ID] = id
    }

    private fun storeProfileType(profileType: Int) {
        prefs[Constants.PREF_PROFILE_TYPE] = profileType
    }

}