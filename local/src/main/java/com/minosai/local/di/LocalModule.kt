package com.minosai.local.di

import com.minosai.common.Constants.PREF
import com.minosai.local.conductor.ConductorDatabase
import com.minosai.local.passenger.PassengerDatabase
import com.minosai.local.util.PreferenceHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

private const val CONDUCTOR_DATABASE = "CONDUCTOR_DATABASE"
private const val PASSENGER_DATABASE = "PASSENGER_DATABASE"

val localModule = module {

    single(CONDUCTOR_DATABASE) { ConductorDatabase.buildDatabase(androidContext()) }
    factory { (get(CONDUCTOR_DATABASE) as ConductorDatabase).conductorDao() }

    single(PASSENGER_DATABASE) { PassengerDatabase.buildDatabase(androidContext()) }
    factory { (get(PASSENGER_DATABASE) as PassengerDatabase).passengerDao() }

    single { PreferenceHelper.customPrefs(androidContext(), PREF) }
}