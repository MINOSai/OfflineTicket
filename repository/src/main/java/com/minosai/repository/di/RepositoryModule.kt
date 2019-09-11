package com.minosai.repository.di

import com.minosai.repository.AuthRepository
import com.minosai.repository.ConductorRepository
import com.minosai.repository.PassengerRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { AuthRepository(get()) }
    factory { ConductorRepository(get(), get()) }
    factory { PassengerRepository(get(), get()) }
}