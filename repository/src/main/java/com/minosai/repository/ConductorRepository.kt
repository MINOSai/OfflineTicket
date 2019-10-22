package com.minosai.repository

import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.minosai.local.conductor.ConductorDao
import com.minosai.model.Result
import com.minosai.remote.conductor.ConductorWebClient
import kotlinx.coroutines.Dispatchers

class ConductorRepository (private val webClient: ConductorWebClient,
                           private val dao: ConductorDao) : BaseRepo() {

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

    fun getAllTickets() = makeRequestAndSave(
        databaseQuery = {
            dao.getAllTickets()
        },
        networkCall = {
            webClient.fetchTickets()
        },
        saveCallResult = {
            dao.save(it)
        }
    )

}