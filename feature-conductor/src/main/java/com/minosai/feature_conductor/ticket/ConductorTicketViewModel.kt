package com.minosai.feature_conductor.ticket

import com.minosai.common.base.BaseViewModel
import com.minosai.model.Ticket
import com.minosai.repository.ConductorRepository
import java.text.SimpleDateFormat
import java.util.*

class ConductorTicketViewModel (private val repo: ConductorRepository) : BaseViewModel() {

    fun generateId(): String {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR)
        val minute = calendar.get(Calendar.MINUTE)
        val second = calendar.get(Calendar.SECOND)
        return "$hour$minute$second"
    }

    fun addTicket(ticket: Ticket) {
        repo.addTicket(ticket)
    }

    fun getTimeStamp(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        sdf.timeZone = TimeZone.getTimeZone("GMT")
        return sdf.format(Date())
    }
}