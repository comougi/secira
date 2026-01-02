package com.ougi.serica.useriimpl.domain.di

import com.ougi.serica.useriapi.domain.UserDomainApi
import org.koin.core.scope.Scope
import org.koin.dsl.koinApplication

object UserDomainApiProvider {

    context(_: Scope)
    fun provideUserApi(): UserDomainApi =
        koinApplication {
            modules(
                createUserDomainApiModule()
            )
        }
            .koin
            .get<UserDomainApi>()
}