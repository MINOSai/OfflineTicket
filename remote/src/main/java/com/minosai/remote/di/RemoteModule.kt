package com.minosai.remote.di

import com.minosai.remote.auth.AuthWebClient
import com.minosai.remote.auth.AuthWebService
import com.minosai.remote.conductor.ConductorWebClient
import com.minosai.remote.conductor.ConductorWebService
import com.minosai.remote.passenger.PassengerWebClient
import com.minosai.remote.passenger.PassengerWebService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://bleh"

val remoteModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory { get<Retrofit>().create(AuthWebService::class.java) }
    factory { AuthWebClient(get()) }

    factory { get<Retrofit>().create(ConductorWebService::class.java) }
    factory { ConductorWebClient(get()) }

    factory { get<Retrofit>().create(PassengerWebService::class.java) }
    factory { PassengerWebClient(get()) }
}