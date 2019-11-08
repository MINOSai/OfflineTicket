package com.minosai.repository

import android.content.SharedPreferences
import androidx.lifecycle.liveData
import com.minosai.common.Constants
import com.minosai.local.passenger.PassengerDao
import com.minosai.local.util.PreferenceHelper.get
import com.minosai.local.util.PreferenceHelper.set
import com.minosai.model.Result
import com.minosai.model.Ticket
import com.minosai.model.passenger.BalanceModel
import com.minosai.remote.passenger.PassengerWebClient
import com.minosai.repository.util.BaseRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PassengerRepository(
    private val webClient: PassengerWebClient,
    private val dao: PassengerDao,
    private val prefs: SharedPreferences
) : BaseRepo() {

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

    fun subtractBalance(amount: Int) {
        val balance = getBalance()
        setBalance(balance - amount)
    }

    fun getUserId() =
        prefs[Constants.PREF_USER_ID, 0] ?: 0

    private fun setBalance(amount: Int) {
        prefs[Constants.PREF_PASSENGER_BALANCE] = amount
    }

    fun getBalance() =
        prefs[Constants.PREF_PASSENGER_BALANCE, 0] ?: 0

    fun fetchBalance() = liveData(Dispatchers.IO) {
        emit(getBalance())

        val response = webClient.addBalance(BalanceModel(0))

        if (response.status == Result.Status.SUCCESS) {
            setBalance(response.data?.balance ?: getBalance())
            emit(getBalance())
        }
    }

    suspend fun addTicket(ticket: Ticket) = withContext(Dispatchers.IO) {
        dao.save(ticket)
    }

    fun addBalanceToServer(amount: Int) = makeRequest(
        request = {
            webClient.addBalance(BalanceModel(amount))
        },
        onSuccess = { response ->
            response?.let {
                setBalance(it.balance)
            }
        }
    )
}