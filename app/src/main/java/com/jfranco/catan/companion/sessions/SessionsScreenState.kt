package com.jfranco.catan.companion.sessions

import com.jfranco.catan.companion.Session

data class SessionsScreenState(
    val loading: Boolean,
    val openDialog: Boolean,
    val sessions: List<Session>
) {
    companion object {
        val initial = SessionsScreenState(
            loading = true,
            openDialog = false,
            sessions = emptyList(),
        )
    }
}