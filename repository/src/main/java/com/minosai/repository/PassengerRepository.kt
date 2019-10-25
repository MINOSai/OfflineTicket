package com.minosai.repository

import android.content.SharedPreferences
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.minosai.common.Constants
import com.minosai.local.passenger.PassengerDao
import com.minosai.local.util.PreferenceHelper.get
import com.minosai.local.util.PreferenceHelper.set
import com.minosai.model.Result
import com.minosai.model.Ticket
import com.minosai.remote.passenger.PassengerWebClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PassengerRepository (private val webClient: PassengerWebClient,
                           private val dao: PassengerDao,
                           private val prefs: SharedPreferences) {

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

    fun getPhoneNumber() =
        prefs[Constants.PREF_PHONE_NUMBER, ""] ?: ""

    fun setBalance(amount: Int) {
        prefs[Constants.PREF_PASSENGER_BALANCE] = amount
    }

    fun addBalance(amount: Int) {
        val balance = getBalance()
        setBalance(balance + amount)
    }

    fun subtractBalance(amount: Int) {
        val balance = getBalance()
        setBalance(balance - amount)
    }

    fun getBalance() =
        prefs[Constants.PREF_PASSENGER_BALANCE, 0] ?: 0

    fun addTicket(ticket: Ticket) = GlobalScope.launch(Dispatchers.IO) {
        dao.save(ticket)
    }
}