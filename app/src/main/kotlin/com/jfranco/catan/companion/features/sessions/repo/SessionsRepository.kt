package com.jfranco.catan.companion.features.sessions.repo

import com.jfranco.catan.companion.Session

interface SessionsRepository {
    fun all(): List<Session>
    fun create()
}