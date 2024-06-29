package com.jfranco.catan.companion.features.sessions.handlers

import com.jfranco.catan.companion.common.handler.ActionHandler
import com.jfranco.catan.companion.features.sessions.Action
import com.jfranco.catan.companion.features.sessions.SessionsScreenState

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
