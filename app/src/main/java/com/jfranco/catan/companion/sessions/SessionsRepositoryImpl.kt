package com.jfranco.catan.companion.sessions

import com.jfranco.catan.companion.Session
import com.jfranco.catan.companion.SessionQueries
import com.jfranco.catan.companion.data.SessionsRepository
import java.util.Calendar

class SessionsRepositoryImpl(
    private val sessionQueries: SessionQueries
) : SessionsRepository {
    override fun all(): List<Session> = sessionQueries.selectAll().executeAsList()
    override fun create() {
        val time = Calendar.getInstance().time.toString()
        sessionQueries.create(time)
    }
}
