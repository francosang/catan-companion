package com.jfranco.catan.companion

import android.app.Application
import com.jfranco.catan.companion.data.databaseModule
import com.jfranco.catan.companion.sessions.sessionsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApp)
            modules(
                databaseModule,
                sessionsModule,
            )
        }
    }

}