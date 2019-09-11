package com.minosai.local.di

import androidx.room.RoomDatabase
import com.minosai.local.conductor.ConductorDatabase
import com.minosai.local.passenger.PassengerDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val CONDUCTOR_DATABASE = "CONDUCTOR_DATABASE"
private const val PASSENGER_DATABASE = "PASSENGER_DATABASE"

val localModule = module {

    single(named(CONDUCTOR_DATABASE)) { ConductorDatabase.buildDatabase(androidContext()) }
    factory { get<ConductorDatabase>(named(CONDUCTOR_DATABASE)).conductorDao() }

    single(named(PASSENGER_DATABASE)) { PassengerDatabase.buildDatabase(androidContext()) }
    factory { get<PassengerDatabase>(named(PASSENGER_DATABASE)).passengerDao() }
}