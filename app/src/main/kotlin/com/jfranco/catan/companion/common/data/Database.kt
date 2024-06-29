package com.jfranco.catan.companion.common.data

import app.cash.sqldelight.db.SqlDriver
import com.jfranco.catan.companion.Database
import com.jfranco.catan.companion.Session
import com.jfranco.catan.companion.common.data.impl.AndroidDatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

interface DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

val databaseModule = module {
    single<DatabaseDriverFactory> {
        AndroidDatabaseDriverFactory(
            context = androidContext()
        )
    }
    single<Database> { Database(get<DatabaseDriverFactory>().createDriver()) }
}
