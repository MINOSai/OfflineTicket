package com.minosai.offlineticket.di

import com.minosai.feature_auth.di.featureAuthModule
import com.minosai.feature_conductor.di.featureConductorModule
import com.minosai.feature_passenger.di.featurePassengerModule
import com.minosai.local.di.localModule
import com.minosai.remote.di.remoteModule
import com.minosai.repository.di.repositoryModule

val appComponent = listOf(
    featureAuthModule,
    featureConductorModule,
    featurePassengerModule,
    localModule,
    remoteModule,
    repositoryModule
)