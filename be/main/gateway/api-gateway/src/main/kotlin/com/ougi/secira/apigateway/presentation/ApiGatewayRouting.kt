package com.ougi.secira.apigateway.presentation

import com.ougi.secira.apigateway.domain.ServiceEndpointsRegistry
import com.ougi.secira.network.utils.redirectRequestToInternalNetwork
import io.ktor.client.HttpClient
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.request.path
import io.ktor.server.response.respond
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

internal fun Application.configureRouting() {
    val serviceEndpointsRegistry by inject<ServiceEndpointsRegistry>()
    val httpClient by inject<HttpClient>()
    routing {
        route("/*") {
            handle {
                serviceEndpointsRegistry.serviceEndpointsCache[call.request.path()]
                    ?.let { endpoint ->
                        val serviceName =
                            if (endpoint.isWhiteListedForAuth) endpoint.serviceName
                            else "authentication"
                        redirectRequestToInternalNetwork(
                            httpClient = httpClient,
                            serviceName = serviceName,
                            includeServiceNameHeader = true,
                        )
                    } ?: call.respond(HttpStatusCode.NotFound, "Unknown endpoint")
            }
        }
    }
}