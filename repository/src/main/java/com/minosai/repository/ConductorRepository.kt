package com.minosai.repository

import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.minosai.local.conductor.ConductorDao
import com.minosai.model.Result
import com.minosai.remote.conductor.ConductorWebClient
import kotlinx.coroutines.Dispatchers

class ConductorRepository (private val conductorWebClient: ConductorWebClient,
                           private val conductorDao: ConductorDao) {

    fun uploadTickets() = liveData(Dispatchers.IO) {
        emit(Result.loading())

        val tickets = conductorDao.getTicketsToUpload()

        val response = conductorWebClient.uploadTickets(tickets)

        when (response.status) {
            Result.Status.SUCCESS -> {
                Result.success("Tickets uploaded successfully")
            }
            Result.Status.ERROR -> {
                emit(Result.error<String>(response.message!!))
            }
            else -> {

            }
        }
    }

    fun getAllTickets() = liveData(Dispatchers.IO) {
        emit(Result.loading())

        val source = conductorDao.getAllTickets().map { Result.success(it) }
        emitSource(source)

        val response = conductorWebClient.fetchTickets()
        when (response.status) {
            Result.Status.SUCCESS -> {
                conductorDao.save(response.data!!)
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