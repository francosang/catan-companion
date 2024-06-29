package com.jfranco.catan.companion.sessions.handlers

import com.jfranco.catan.companion.handler.ActionHandler
import com.jfranco.catan.companion.sessions.repo.SessionsRepository
import com.jfranco.catan.companion.sessions.Action
import com.jfranco.catan.companion.sessions.SessionsScreenState

class CreateSessionHandler(
    private val sessionsRepository: SessionsRepository
) : ActionHandler<Action.Confirm, SessionsScreenState>() {
    override fun handle(
        action: Action.Confirm,
        state: SessionsScreenState
    ): SessionsScreenState {
        sessionsRepository.create()
        val sessions = sessionsRepository.all()
        return state.copy(
            openDialog = false,
            sessions = sessions
        )
    }
}
