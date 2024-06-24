package com.jfranco.catan.companion.sessions

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.jfranco.catan.companion.data.SessionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

sealed class Action {
    data object NewSession : Action()
    data object Cancel : Action()
    data object Confirm : Action()
}

class SessionsScreenModel(
    private val sessionsRepository: SessionsRepository
) : StateScreenModel<SessionsScreenState>(SessionsScreenState.initial) {

    fun action(action: Action) {

        when (action) {
            Action.NewSession -> mutableState.value = mutableState.value.copy(
                openDialog = true
            )

            Action.Cancel -> mutableState.value = mutableState.value.copy(
                openDialog = false
            )

            Action.Confirm -> createNewSession()
        }

    }

    private fun createNewSession() {
        screenModelScope.launch {
            withContext(Dispatchers.IO) {
                sessionsRepository.create()
                val sessions = sessionsRepository.all()
                mutableState.value = mutableState.value.copy(
                    openDialog = false,
                    sessions = sessions
                )
            }
        }
    }

    init {
        screenModelScope.launch {
            withContext(Dispatchers.IO) {
                val sessions = sessionsRepository.all()
                mutableState.value = mutableState.value.copy(
                    loading = false,
                    sessions = sessions
                )
            }
        }
    }

}