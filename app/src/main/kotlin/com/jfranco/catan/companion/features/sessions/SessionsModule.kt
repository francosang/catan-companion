package com.jfranco.catan.companion.features.sessions

import com.jfranco.catan.companion.Database
import com.jfranco.catan.companion.features.sessions.handlers.LoadSessionsHandler
import com.jfranco.catan.companion.features.sessions.handlers.CreateSessionHandler
import com.jfranco.catan.companion.features.sessions.handlers.OpenConfirmationHandler
import com.jfranco.catan.companion.features.sessions.handlers.CloseConfirmationAlertHandler
import com.jfranco.catan.companion.features.sessions.repo.SessionsRepository
import com.jfranco.catan.companion.features.sessions.repo.impl.SessionsRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val sessionsModule = module {
    single<SessionsRepository> {
        SessionsRepositoryImpl(get<Database>().sessionQueries)
    }
    singleOf(::LoadSessionsHandler)
    singleOf(::CreateSessionHandler)
    singleOf(::OpenConfirmationHandler)
    singleOf(::CloseConfirmationAlertHandler)
    singleOf(::SessionsScreenModel)
}
