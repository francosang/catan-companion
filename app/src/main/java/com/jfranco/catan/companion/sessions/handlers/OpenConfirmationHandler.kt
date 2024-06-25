package com.jfranco.catan.companion.sessions.handlers

import com.jfranco.catan.companion.handler.ActionHandler
import com.jfranco.catan.companion.sessions.Action
import com.jfranco.catan.companion.sessions.SessionsScreenState

class OpenConfirmationHandler : ActionHandler<Action.NewSession, SessionsScreenState>() {
    override fun handle(
        action: Action.NewSession,
        state: SessionsScreenState,
    ): SessionsScreenState {
        return state.copy(
            openDialog = true
        )
    }
}
