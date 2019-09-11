package com.minosai.offlineticket.di

import com.minosai.local.di.localModule
import com.minosai.remote.di.remoteModule
import com.minosai.repository.di.repositoryModule

val appComponent = listOf(
    remoteModule,
    localModule,
    repositoryModule
)