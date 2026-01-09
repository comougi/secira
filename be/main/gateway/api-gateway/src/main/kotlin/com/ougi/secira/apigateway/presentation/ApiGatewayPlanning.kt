package com.ougi.secira.apigateway.presentation

import com.ougi.secira.apigateway.domain.ServiceEndpointsRegistry
import io.ktor.server.application.Application
import org.koin.ktor.ext.inject

internal fun Application.configurePlanning() {
    val serviceEndpointsRegistry by inject<ServiceEndpointsRegistry>()
    serviceEndpointsRegistry.startCacheUpdating()
}