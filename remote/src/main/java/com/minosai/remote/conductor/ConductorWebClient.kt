package com.minosai.remote.conductor

import com.minosai.model.Ticket
import com.minosai.remote.util.BaseApiClient

class ConductorWebClient(private val service: ConductorWebService) : BaseApiClient() {

    suspend fun uploadTickets(tickets: List<Ticket>) = getResult {
        service.uploadTickets(tickets)
    }

    suspend fun fetchTickets() = getResult {
        service.fetchTickets()
    }
}