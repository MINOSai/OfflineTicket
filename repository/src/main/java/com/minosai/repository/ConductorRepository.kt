package com.minosai.repository

import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.minosai.local.conductor.ConductorDao
import com.minosai.model.Result
import com.minosai.remote.conductor.ConductorWebClient
import kotlinx.coroutines.Dispatchers

class ConductorRepository (private val webClient: ConductorWebClient,
                           private val dao: ConductorDao) {

    fun uploadTickets() = liveData(Dispatchers.IO) {
        emit(Result.loading())

        val tickets = dao.getTicketsToUpload()

        val response = webClient.uploadTickets(tickets)

        when (response.status) {
            Result.Status.SUCCESS -> {
                emit(Result.success("Tickets uploaded successfully"))
            }
            Result.Status.ERROR -> {
                emit(Result.error(response.message!!))
            }
            else -> {

            }
        }
    }

    fun getAllTickets() = liveData(Dispatchers.IO) {
        emit(Result.loading())

        val source = dao.getAllTickets().map { Result.success(it) }
        emitSource(source)

        val response = webClient.fetchTickets()
        when (response.status) {
            Result.Status.SUCCESS -> {
                dao.save(response.data!!)
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