package com.minosai.feature_conductor.di

import com.minosai.feature_conductor.home.ConductorHomeViewModel
import com.minosai.feature_conductor.ticket.ConductorTicketViewModel
import org.koin.android.viewmodel.experimental.builder.viewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val featureConductorModule = module {
    viewModel { ConductorHomeViewModel(get()) }
    viewModel { ConductorTicketViewModel(get()) }
}