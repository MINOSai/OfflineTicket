package com.minosai.feature_passenger.balance

import androidx.lifecycle.ViewModel
import com.minosai.repository.PassengerRepository

class BalanceViewModel(private val repo: PassengerRepository) : ViewModel() {

    fun getBalance() = repo.getBalance()

    fun addBalanceToServer(amount: Int) = repo.addBalanceToServer(amount)
}
