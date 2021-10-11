package com.endrjudev.githubapidemo

import android.app.Application
import com.endrjudev.data.DataDI
import com.endrjudev.domain.DomainDI
import com.endrjudev.presentation.PresentationDI
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(androidContext = this@App)
            modules(
                listOf(
                    DataDI.dataModule,
                    DomainDI.domainModule,
                    PresentationDI.presentationModule
                )
            )
        }
    }
}