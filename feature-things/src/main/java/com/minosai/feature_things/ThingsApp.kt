package com.minosai.feature_things

import android.app.Application
import com.minosai.feature_things.di.thingsAppComponent
import org.koin.android.ext.android.startKoin

class ThingsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, thingsAppComponent)
    }

}