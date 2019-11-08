package com.minosai.feature_auth

import androidx.lifecycle.ViewModel
import com.minosai.repository.AuthRepository

class AuthViewModel(private val repo: AuthRepository) : ViewModel() {

    var profileType = 0

    fun login(userName: String, password: String) =
        repo.login(userName, password, profileType)

    fun signup(userName: String, password: String) =
        repo.signup(userName, password, profileType)
}
