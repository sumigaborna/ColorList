package com.sumigaborna.colorlist

import android.app.Application
import com.sumigaborna.colorlist.di.adaptersModule
import com.sumigaborna.colorlist.di.databaseModule
import com.sumigaborna.colorlist.di.repositoryModule
import com.sumigaborna.colorlist.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class ColorListApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ColorListApp)
            loadKoinModules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    databaseModule,
                    adaptersModule
                )
            )
        }
    }
}