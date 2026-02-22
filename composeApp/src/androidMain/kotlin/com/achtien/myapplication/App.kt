package com.achtien.myapplication

import android.app.Application
import di.bluetoothModule
import di.networkingModule
import di.repositoryModule
import di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import platformModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                listOf(
                    viewModelModule,
                    bluetoothModule(),
                    repositoryModule,
                    platformModule(),
                    networkingModule(true)
                )
            )
        }
    }
}
