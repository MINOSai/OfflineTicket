package com.minosai.offlineticket

import android.app.Application
import com.minosai.offlineticket.di.appComponent
import org.koin.android.ext.android.startKoin

open class App : Application() {

    override fun onCreate() {
        super.onCreate()

//        startKoin {
//            androidLogger()
//            androidContext(this@App)
//            appComponent
//        }

        startKoin(this, appComponent)
    }
}