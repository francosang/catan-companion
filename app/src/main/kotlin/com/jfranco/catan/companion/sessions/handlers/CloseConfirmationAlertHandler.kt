package com.jfranco.catan.companion.sessions.handlers

import com.jfranco.catan.companion.handler.ActionHandler
import com.jfranco.catan.companion.sessions.Action
import com.jfranco.catan.companion.sessions.SessionsScreenState

class CloseConfirmationAlertHandler : ActionHandler<Action.Cancel, SessionsScreenState>() {
    override fun handle(action: Action.Cancel, state: SessionsScreenState): SessionsScreenState {
        return state.copy(
            openDialog = false
        )
    }
}
