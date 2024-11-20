package com.achtien.codingtemplate

import android.app.Application
import com.achtien.codingtemplate.di.appModule
import com.achtien.codingtemplate.di.networkingModule
import com.achtien.codingtemplate.di.repositoryModule
import com.achtien.codingtemplate.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(
                appModule, viewModelModule, repositoryModule, networkingModule(true)
            ))
        }
    }
}
