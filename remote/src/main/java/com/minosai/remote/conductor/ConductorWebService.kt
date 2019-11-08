package com.minosai.remote.conductor

import com.minosai.model.Ticket
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ConductorWebService {

    @POST("ticket/bulk/")
    suspend fun uploadTickets(@Body tickets: List<Ticket>): Response<List<Ticket>>

    @GET("ticket/")
    suspend fun fetchTickets() : Response<List<Ticket>>
}