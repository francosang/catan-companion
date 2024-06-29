package com.jfranco.catan.companion.sessions

import com.jfranco.catan.companion.Session
import com.jfranco.catan.companion.sessions.handlers.CloseConfirmationAlertHandler
import com.jfranco.catan.companion.sessions.handlers.CreateSessionHandler
import com.jfranco.catan.companion.sessions.handlers.LoadSessionsHandler
import com.jfranco.catan.companion.sessions.handlers.OpenConfirmationHandler
import com.jfranco.catan.companion.state.ScreenActionModel

data class SessionsScreenState(
    val loading: Boolean,
    val openDialog: Boolean,
    val sessions: List<Session>
) {
    companion object {
        val Empty = SessionsScreenState(
            loading = true,
            openDialog = false,
            sessions = emptyList(),
        )
    }
}

sealed class Action {
    data object LoadSessions : Action()
    data object NewSession : Action()
    data object Cancel : Action()
    data object Confirm : Action()
}

class SessionsScreenModel(
    private val loadSessionsHandler: LoadSessionsHandler,
    private val createSessionHandler: CreateSessionHandler,
    private val openConfirmationHandler: OpenConfirmationHandler,
    private val closeConfirmationAlertHandler: CloseConfirmationAlertHandler,
) : ScreenActionModel<Action, SessionsScreenState>(Action.LoadSessions, SessionsScreenState.Empty) {

    override fun handlers(
        action: Action,
        currentState: SessionsScreenState
    ): SessionsScreenState {
        return when (action) {
            is Action.LoadSessions -> loadSessionsHandler(action, currentState)
            is Action.NewSession -> openConfirmationHandler(action, currentState)
            is Action.Cancel -> closeConfirmationAlertHandler(action, currentState)
            is Action.Confirm -> createSessionHandler(action, currentState)
        }
    }
}
