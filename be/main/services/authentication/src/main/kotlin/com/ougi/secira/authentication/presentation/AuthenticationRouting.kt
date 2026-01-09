package com.ougi.secira.authentication.presentation

import com.ougi.secira.authentication.domain.security.TokenFactory
import com.ougi.secira.authentication.presentation.mapper.toPresentation
import com.ougi.secira.authentication.presentation.model.CreateTokensRequest
import com.ougi.secira.ktorutils.getStringProperty
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.principal
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

internal fun Application.configureRouting() {
    val serviceName = getStringProperty("service-name")
    routing {
        configureJwksJsonRouting()
        route("/${serviceName}") {
            route("/token") {
                createTokens()
                refreshToken()
            }
        }
        validateJwtRouting()
    }
}

internal fun Route.createTokens() {
    val tokenFactory by inject<TokenFactory>()
    post("/create") {
        val createTokensRequest = call.receive<CreateTokensRequest>()
        val tokenPair = tokenFactory.createTokenPair(createTokensRequest.username)
        call.respond(tokenPair.toPresentation())
    }
}

internal fun Route.refreshToken() {
    val tokenFactory by inject<TokenFactory>()
    authenticate("refresh-user-access-token") {
        post("/refresh") {
            call.principal<UserIdPrincipal>()
                ?.name
                ?.let { username -> tokenFactory.createTokenPair(username).toPresentation() }
                ?.let { responseData -> call.respond(responseData) }
                ?: call.respond(HttpStatusCode.Unauthorized)
        }
    }
}