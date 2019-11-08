package com.minosai.model.auth

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    val access: String,
    val refresh: String,
    @SerializedName("user_id", alternate = ["id"]) val id: Int
)