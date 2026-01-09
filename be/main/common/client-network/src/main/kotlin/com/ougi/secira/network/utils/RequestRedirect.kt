package com.ougi.secira.network.utils

import io.ktor.client.HttpClient
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import io.ktor.server.plugins.origin
import io.ktor.server.request.host
import io.ktor.server.request.httpMethod
import io.ktor.server.request.path
import io.ktor.server.request.receiveChannel
import io.ktor.server.response.respond
import io.ktor.server.routing.RoutingContext
import com.ougi.secira.network.utils.HttpHeaders as SeciraHttpHeaders

suspend fun RoutingContext.redirectRequestToInternalNetwork(
    httpClient: HttpClient,
    serviceName: String,
    includeServiceNameHeader: Boolean
) {
    val path = call.request.path()
    val targetBaseUrl = "http://$serviceName"
    val targetUrl = "$targetBaseUrl$path"

    val proxyResponse =
        httpClient.request(targetUrl) {
            method = call.request.httpMethod
            call.request
                .queryParameters
                .forEach(this::parameter)
            headers {
                appendAll(call.request.headers)
                val remoteHost = call.request.origin.remoteHost
                val existingXff = call.request.headers[HttpHeaders.XForwardedFor]
                val newXff =
                    if (existingXff.isNullOrBlank()) remoteHost
                    else "$existingXff, $remoteHost"
                set(HttpHeaders.XForwardedFor, newXff)
                set(HttpHeaders.XForwardedProto, call.request.origin.scheme)
                set(HttpHeaders.XForwardedHost, call.request.host())
                if (includeServiceNameHeader) set(SeciraHttpHeaders.ServiceName, serviceName)
            }
            setBody(call.receiveChannel())
        }

    call.respond(proxyResponse)
}