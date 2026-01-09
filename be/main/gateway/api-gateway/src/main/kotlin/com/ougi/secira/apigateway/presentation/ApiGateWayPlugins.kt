package com.ougi.secira.apigateway.presentation

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.forwardedheaders.ForwardedHeaders

internal fun Application.configurePlugins() {
    install(ForwardedHeaders) {
        useFirstValue()
    }
}