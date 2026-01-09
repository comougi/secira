package com.ougi.secira.authentication.presentation

import com.auth0.jwk.JwkProvider
import com.ougi.secira.authentication.domain.security.TYPE_CLAIM
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.AuthenticationConfig
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.jwt.JWTAuthenticationProvider
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import org.koin.core.qualifier.named
import org.koin.ktor.ext.inject

internal fun Application.configurePlugins() {
    val issuer by inject<String>(named("issuer"))

    val accessTokenJwkProvider by inject<JwkProvider>(named("access"))
    val refreshTokenJwkProvider by inject<JwkProvider>(named("refresh"))

    install(Authentication) {
        configureAccessTokenVerification(issuer, accessTokenJwkProvider)
        configureRefreshTokenVerification(issuer, refreshTokenJwkProvider)
    }
    install(ContentNegotiation) { json() }
}


private fun AuthenticationConfig.configureRefreshTokenVerification(
    issuer: String,
    jwkProvider: JwkProvider,
) =
    jwt("refresh-user-access-token") {
        verifier(jwkProvider, issuer) {
            withClaim(TYPE_CLAIM, "refreshToken")
        }
        validateUsername()
    }


private fun AuthenticationConfig.configureAccessTokenVerification(
    issuer: String,
    jwkProvider: JwkProvider,
) =
    jwt("auth-jwt") {
        verifier(jwkProvider, issuer) {
            withClaim(TYPE_CLAIM, "accessToken")
            acceptLeeway(3)
        }
        validateUsername()
    }

private fun JWTAuthenticationProvider.Config.validateUsername() =
    validate { credentials -> credentials["username"]?.let(::UserIdPrincipal) }