package com.minosai.model.passenger

import com.google.gson.annotations.SerializedName

data class BalanceModel(
    @SerializedName("amount", alternate = ["balance"])
    val balance: Int
)