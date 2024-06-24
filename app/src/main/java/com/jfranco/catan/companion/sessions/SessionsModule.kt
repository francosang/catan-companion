package com.jfranco.catan.companion.sessions

import com.jfranco.catan.companion.Database
import com.jfranco.catan.companion.data.SessionsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val sessionsModule = module {
    single<SessionsRepository> {
        SessionsRepositoryImpl(get<Database>().sessionQueries)
    }
    singleOf(::SessionsScreenModel)
}
