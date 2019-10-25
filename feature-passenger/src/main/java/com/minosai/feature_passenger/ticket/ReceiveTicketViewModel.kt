package com.minosai.feature_passenger.ticket

import com.minosai.common.base.BaseViewModel
import com.minosai.model.Ticket
import com.minosai.repository.PassengerRepository

class ReceiveTicketViewModel(private val repo: PassengerRepository) : BaseViewModel() {

    fun addTicket(ticket: Ticket) {
        repo.addTicket(ticket)
    }

    fun reduceBalance(amount: Int) {
        repo.subtractBalance(amount)
    }
}
