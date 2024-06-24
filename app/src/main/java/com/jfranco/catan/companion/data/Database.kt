package com.jfranco.catan.companion.data

import app.cash.sqldelight.db.SqlDriver
import com.jfranco.catan.companion.Database
import com.jfranco.catan.companion.Session
import com.jfranco.catan.companion.data.impl.AndroidDatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

interface DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

interface SessionsRepository {
    fun all(): List<Session>
    fun create()
}


val databaseModule = module {
    single<DatabaseDriverFactory> {
        AndroidDatabaseDriverFactory(
            context = androidContext()
        )
    }
    single<Database> { Database(get<DatabaseDriverFactory>().createDriver()) }
}
