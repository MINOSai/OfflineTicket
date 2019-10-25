package com.minosai.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Ticket(

    @PrimaryKey
    @SerializedName("id")
    val id: String,

    @SerializedName("source")
    val source: String,

    @SerializedName("destination")
    val destination: String,

    @SerializedName("amount")
    val amount: String,

    @SerializedName("timestamp")
    val timestamp: String?
)
