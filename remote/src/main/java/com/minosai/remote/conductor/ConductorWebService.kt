package com.minosai.remote.conductor

import com.minosai.model.Ticket
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ConductorWebService {

    @POST("tickets")
    suspend fun uploadTickets(@Body tickets: List<Ticket>) : Response<String>

    @GET("tickets")
    suspend fun fetchTickets() : Response<List<Ticket>>
}