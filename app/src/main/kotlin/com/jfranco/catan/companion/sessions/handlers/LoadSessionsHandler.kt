package com.jfranco.catan.companion.sessions.handlers

import com.jfranco.catan.companion.handler.ActionHandler
import com.jfranco.catan.companion.sessions.repo.SessionsRepository
import com.jfranco.catan.companion.sessions.Action
import com.jfranco.catan.companion.sessions.SessionsScreenState

class LoadSessionsHandler(
    private val sessionsRepository: SessionsRepository
) : ActionHandler<Action.LoadSessions, SessionsScreenState>() {
    override fun handle(
        action: Action.LoadSessions,
        state: SessionsScreenState,
    ): SessionsScreenState {
        val sessions = sessionsRepository.all()
        return state.copy(
            loading = false,
            sessions = sessions
        )
    }
}
