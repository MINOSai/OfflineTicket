package com.minosai.feature_passenger.details

import androidx.lifecycle.ViewModel
import com.minosai.common.base.BaseViewModel
import com.minosai.repository.PassengerRepository

class SendDetailsViewModel(private val repo: PassengerRepository) : BaseViewModel() {

    fun getPhoneNumber() = repo.getPhoneNumber()

    fun getBalance() = repo.getBalance()
}
