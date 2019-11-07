package com.minosai.feature_conductor.ticket

import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.minosai.common.base.BaseViewModel
import com.minosai.model.Ticket
import com.minosai.repository.ConductorRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ConductorTicketViewModel(private val repo: ConductorRepository) : BaseViewModel() {

    private var passengerId = 0
    private lateinit var id: String

    fun generateId(): String {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR)
        val minute = calendar.get(Calendar.MINUTE)
        val second = calendar.get(Calendar.SECOND)
        id = "$hour$minute$second"
        return id
    }

    private fun getTimeStamp(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        sdf.timeZone = TimeZone.getTimeZone("GMT")
        return sdf.format(Date())
    }

    private fun getPassengerDetails(identifier: String): List<String> {
        val collectionType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson<List<String>>(identifier, collectionType)
    }

    fun setPassengerIdFromIdentifier(identifier: String) {
        val userDetails = getPassengerDetails(identifier)
        passengerId = userDetails[0].toInt()
    }

    fun generateTicket(source: String, destination: String, amount: String) = Ticket(
        id,
        source,
        destination,
        amount.toInt(),
        getTimeStamp(),
        repo.getUserId(),
        passengerId
    )

    fun getPayload(ticket: Ticket): ByteArray {
        val ticketDetails = listOf(
            ticket.id,
            ticket.source,
            ticket.destination,
            ticket.amount
        )
        val ticketJson = Gson().toJson(ticketDetails)
        return ticketJson.toByteArray()
    }

    fun addTicket(ticket: Ticket) = viewModelScope.launch {
        repo.addTicket(ticket)
    }
}