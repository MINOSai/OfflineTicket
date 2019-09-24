package com.minosai.feature_passenger.di

import com.minosai.feature_passenger.balance.BalanceViewModel
import com.minosai.feature_passenger.details.SendDetailsViewModel
import com.minosai.feature_passenger.home.PassengerHomeViewModel
import com.minosai.feature_passenger.ticket.ReceiveTicketViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val featurePassengerModule = module {
    viewModel { BalanceViewModel(get())}
    viewModel { SendDetailsViewModel(get()) }
    viewModel { PassengerHomeViewModel(get()) }
    viewModel { ReceiveTicketViewModel(get()) }
}