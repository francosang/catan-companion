package com.jfranco.catan.companion.features.sessions.handlers

import com.jfranco.catan.companion.common.handler.ActionHandler
import com.jfranco.catan.companion.features.sessions.Action
import com.jfranco.catan.companion.features.sessions.SessionsScreenState

class CloseConfirmationAlertHandler : ActionHandler<Action.Cancel, SessionsScreenState>() {
    override fun handle(action: Action.Cancel, state: SessionsScreenState): SessionsScreenState {
        return state.copy(
            openDialog = false
        )
    }
}
