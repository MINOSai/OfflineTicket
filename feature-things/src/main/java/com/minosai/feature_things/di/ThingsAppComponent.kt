package com.minosai.feature_things.di

import com.minosai.feature_conductor.di.featureConductorModule
import com.minosai.local.di.localModule
import com.minosai.remote.di.remoteModule
import com.minosai.repository.di.repositoryModule

val thingsAppComponent = listOf(
    featureConductorModule,
    localModule,
    remoteModule,
    repositoryModule
)