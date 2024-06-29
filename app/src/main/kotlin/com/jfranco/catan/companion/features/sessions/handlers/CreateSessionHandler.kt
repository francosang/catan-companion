package com.jfranco.catan.companion.features.sessions.handlers

import com.jfranco.catan.companion.common.handler.ActionHandler
import com.jfranco.catan.companion.features.sessions.repo.SessionsRepository
import com.jfranco.catan.companion.features.sessions.Action
import com.jfranco.catan.companion.features.sessions.SessionsScreenState

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
