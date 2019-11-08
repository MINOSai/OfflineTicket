package com.minosai.feature_passenger.ticket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.minosai.model.Ticket
import com.minosai.repository.PassengerRepository
import kotlinx.coroutines.launch

class ReceiveTicketViewModel(private val repo: PassengerRepository) : ViewModel() {

    private fun addTicket(ticket: Ticket) = viewModelScope.launch {
        repo.addTicket(ticket)
    }

    private fun reduceBalance(amount: Int) {
        repo.subtractBalance(amount)
    }

    private fun getTicketDetails(identifier: String): List<String> {
        val collectionType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson<List<String>>(identifier, collectionType)
    }

    fun addTicketUsingIdentifier(identifier: String) {
        val ticketDetails = getTicketDetails(identifier)

        val ticket = Ticket(
            ticketDetails[0],
            ticketDetails[1],
            ticketDetails[2],
            ticketDetails[3].toInt(),
            null, null, null
        )

        addTicket(ticket)
        reduceBalance(ticket.amount)
    }
}
