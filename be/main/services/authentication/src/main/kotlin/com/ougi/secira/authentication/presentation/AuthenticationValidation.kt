package com.ougi.secira.authentication.presentation

import com.ougi.secira.network.utils.redirectRequestToInternalNetwork
import io.ktor.client.HttpClient
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject
import com.ougi.secira.network.utils.HttpHeaders as SeciraHttpHeaders

internal fun Routing.validateJwtRouting() {
    val httpClient by inject<HttpClient>()
    authenticate("auth-jwt") {
        route("/*") {
            handle {
                call
                    .request
                    .headers[SeciraHttpHeaders.ServiceName]
                    ?.let { serviceName ->
                        redirectRequestToInternalNetwork(
                            httpClient = httpClient,
                            serviceName = serviceName,
                            includeServiceNameHeader = false
                        )
                    } ?: call.respond(HttpStatusCode.BadRequest, "Service-name header doesn't exists")
            }
        }
    }
}