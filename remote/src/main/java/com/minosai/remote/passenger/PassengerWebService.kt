package com.minosai.remote.passenger

import com.minosai.model.Ticket
import com.minosai.model.passenger.BalanceModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PassengerWebService {

    @GET("ticket/")
    suspend fun fetchTickets() : Response<List<Ticket>>

    @POST("passenger/balance/")
    suspend fun addBalance(@Body amount: BalanceModel): Response<BalanceModel>
}