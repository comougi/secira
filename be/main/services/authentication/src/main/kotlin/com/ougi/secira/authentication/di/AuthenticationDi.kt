package com.ougi.secira.authentication.di

import com.ougi.secira.network.di.createClientNetworkModule
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.ktor.plugin.Koin

internal fun Application.configureDi() {
    install(Koin) {
        modules(
            createClientNetworkModule(),
            createAuthenticationModule()
        )
    }
}