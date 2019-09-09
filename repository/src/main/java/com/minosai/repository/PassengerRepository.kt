package com.minosai.repository

import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.minosai.local.passenger.PassengerDao
import com.minosai.model.Result
import com.minosai.remote.passenger.PassengerWebClient
import kotlinx.coroutines.Dispatchers

class PassengerRepository (private val passengerWebClient: PassengerWebClient,
                           private val passengerDao: PassengerDao) {

    fun getAllTickets() = liveData(Dispatchers.IO) {
        emit(Result.loading())

        val source = passengerDao.getAllTickets().map { Result.success(it) }
        emitSource(source)

        val response = passengerWebClient.fetchTickets()
        when (response.status) {
            Result.Status.SUCCESS -> {
                passengerDao.save(response.data!!)
            }
            Result.Status.ERROR -> {
                emit(Result.error(response.message!!))
                emitSource(source)
            }
            else -> {

            }
        }
    }

}