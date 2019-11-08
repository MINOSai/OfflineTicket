package com.minosai.remote.passenger

import com.minosai.model.passenger.BalanceModel
import com.minosai.remote.util.BaseApiClient

class PassengerWebClient(private val service: PassengerWebService) : BaseApiClient() {

    suspend fun fetchTickets() = getResult { service.fetchTickets() }

    suspend fun addBalance(amount: BalanceModel) = getResult { service.addBalance(amount) }
}