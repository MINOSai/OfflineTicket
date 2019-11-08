package com.minosai.feature_passenger.details

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.minosai.repository.PassengerRepository

class SendDetailsViewModel(private val repo: PassengerRepository) : ViewModel() {

    private fun getUserId() = repo.getUserId()

    private fun getBalance() = repo.getBalance()

    fun getPayload(): ByteArray {
        val detailsList = listOf(
            getUserId().toString(),
            getBalance().toString()
        )

        val detailsJson = Gson().toJson(detailsList)
        return detailsJson.toByteArray()
    }
}
