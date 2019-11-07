package com.minosai.feature_passenger.home

import com.minosai.common.base.BaseViewModel
import com.minosai.repository.PassengerRepository

class PassengerHomeViewModel (private val repo: PassengerRepository) : BaseViewModel() {

    fun fetchAllTickets() = repo.getAllTickets()

    fun fetchBalance() = repo.fetchBalance()

    fun getBalance() = repo.getBalance()

}