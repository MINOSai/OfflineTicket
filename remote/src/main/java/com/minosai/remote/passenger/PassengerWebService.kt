package com.minosai.remote.passenger

import com.minosai.model.Ticket
import retrofit2.Response
import retrofit2.http.GET

interface PassengerWebService {

    @GET("tickets")
    suspend fun fetchTickets() : Response<List<Ticket>>
}