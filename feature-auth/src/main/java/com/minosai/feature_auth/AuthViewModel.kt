package com.minosai.feature_auth

import com.minosai.common.base.BaseViewModel
import com.minosai.repository.AuthRepository

class AuthViewModel(private val repo: AuthRepository) : BaseViewModel() {

    var profileType = 0
    var phoneNumber = ""

    fun verifyOtp(otp: String): Boolean {
        repo.setProfileType(profileType)
        repo.storePhNo(phoneNumber)
        return otp == "otp"
    }

}
