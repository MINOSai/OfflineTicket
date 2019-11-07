package com.minosai.remote.di

import com.minosai.remote.auth.AuthWebClient
import com.minosai.remote.auth.AuthWebService
import com.minosai.remote.conductor.ConductorWebClient
import com.minosai.remote.conductor.ConductorWebService
import com.minosai.remote.passenger.PassengerWebClient
import com.minosai.remote.passenger.PassengerWebService
import com.minosai.remote.util.getLoggingInterceptor
import com.minosai.remote.util.getTokenInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://offline-ticket.herokuapp.com/"

private const val TOKEN_INTERCEPTOR = "TOKEN_INTERCEPTOR"
private const val LOGGING_INTERCEPTOR = "LOGGING_INTERCEPTOR"

private const val AUTH_RETROFIT = "AUTH_RETROFIT"
private const val FEATURE_RETROFIT = "FEATURE_RETROFIT"

val remoteModule = module {

    factory(name = TOKEN_INTERCEPTOR) { getTokenInterceptor(get()) }
    factory(name = LOGGING_INTERCEPTOR) { getLoggingInterceptor() }

    single(name = AUTH_RETROFIT) {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get(name = LOGGING_INTERCEPTOR))
            .build()
    }

    single(name = FEATURE_RETROFIT) {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get(name = TOKEN_INTERCEPTOR))
            .build()
    }

    factory { get<Retrofit>(name = AUTH_RETROFIT).create(AuthWebService::class.java) }
    factory { AuthWebClient(get()) }

    factory { get<Retrofit>(name = FEATURE_RETROFIT).create(ConductorWebService::class.java) }
    factory { ConductorWebClient(get()) }

    factory { get<Retrofit>(name = FEATURE_RETROFIT).create(PassengerWebService::class.java) }
    factory { PassengerWebClient(get()) }
}