package com.minosai.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Ticket(
    @PrimaryKey
    val id: String,
    val source: String,
    val destination: String,
    val amount: Int,
    val timestamp: String?,
    val conductor: Int?,
    val passenger: Int?
)
