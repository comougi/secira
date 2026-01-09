package com.ougi.secira.apigateway.di

import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.ktor.plugin.Koin

internal fun Application.configureDi() {
    install(Koin) {
        modules(createApiGatewayModule())
    }
}