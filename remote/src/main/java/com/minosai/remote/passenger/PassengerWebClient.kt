package com.minosai.remote.passenger

import com.minosai.remote.util.BaseApiClient

class PassengerWebClient(private val service: PassengerWebService) : BaseApiClient() {

    suspend fun fetchTickets() = getResult { service.fetchTickets() }

}