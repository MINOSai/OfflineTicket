package com.minosai.repository

import android.content.SharedPreferences
import com.minosai.common.Constants
import com.minosai.local.conductor.ConductorDao
import com.minosai.local.util.PreferenceHelper.get
import com.minosai.model.Ticket
import com.minosai.remote.conductor.ConductorWebClient
import com.minosai.repository.util.BaseRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ConductorRepository(
    private val webClient: ConductorWebClient,
    private val dao: ConductorDao,
    private val prefs: SharedPreferences
) : BaseRepo() {

    fun uploadTickets() = makeRequest(
        request = {
            val tickets = dao.getTicketsToUpload()
            webClient.uploadTickets(tickets)
        },
        onSuccess = {
            dao.deleteTicketsToUpload()
        }
    )

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

    fun getTicketsFromDb() = dao.getAllTickets()

    suspend fun addTicket(ticket: Ticket) = withContext(Dispatchers.IO) {
        dao.save(ticket)
    }

    fun getUserId() =
        prefs[Constants.PREF_USER_ID, 0] ?: 0

}