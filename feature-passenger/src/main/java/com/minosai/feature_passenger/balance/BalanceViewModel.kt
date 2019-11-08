package com.minosai.feature_passenger.balance

import com.minosai.common.base.BaseViewModel
import com.minosai.repository.PassengerRepository

class BalanceViewModel (private val repo: PassengerRepository) : BaseViewModel() {

    fun getBalance() = repo.getBalance()

    fun addBalanceToServer(amount: Int) = repo.addBalanceToServer(amount)
}
