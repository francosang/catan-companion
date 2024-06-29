package com.jfranco.catan.companion.common.data.impl

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.jfranco.catan.companion.Database
import com.jfranco.catan.companion.common.data.DatabaseDriverFactory

class AndroidDatabaseDriverFactory(private val context: Context) : DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(Database.Schema, context, "catan.db")
    }
}