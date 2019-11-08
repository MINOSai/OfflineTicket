package com.minosai.repository.di

import com.minosai.repository.AuthRepository
import com.minosai.repository.ConductorRepository
import com.minosai.repository.PassengerRepository
import org.koin.dsl.module.module

val repositoryModule = module {
    factory { AuthRepository(get(), get()) }
    factory { ConductorRepository(get(), get(), get()) }
    factory { PassengerRepository(get(), get(), get()) }
}