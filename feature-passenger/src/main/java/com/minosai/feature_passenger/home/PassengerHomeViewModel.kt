package com.minosai.feature_passenger.home

import androidx.lifecycle.ViewModel
import com.minosai.repository.PassengerRepository

class PassengerHomeViewModel(private val repo: PassengerRepository) : ViewModel() {

    fun fetchAllTickets() = repo.getAllTickets()

    fun fetchBalance() = repo.fetchBalance()

    fun getBalance() = repo.getBalance()

}