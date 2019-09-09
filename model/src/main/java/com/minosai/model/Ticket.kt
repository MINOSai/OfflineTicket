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

    @SerializedName("date")
    val date: String,

    @SerializedName("time")
    val time: String,

    @SerializedName("bus")
    val bus: String
)
