package com.minosai.model.auth

import com.google.gson.annotations.SerializedName

data class AuthRequest(
    @SerializedName("username") val userName: String,
    @SerializedName("password") val password: String,
    @SerializedName("role") val userType: Int
)