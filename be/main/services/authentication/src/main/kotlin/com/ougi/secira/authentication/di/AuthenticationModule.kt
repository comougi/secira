package com.ougi.secira.authentication.di

import com.ougi.secira.authentication.domain.model.JwksVariables
import com.ougi.secira.authentication.domain.security.TokenFactory
import com.ougi.secira.authentication.domain.usecase.FillJwksFileEnvVariablesUseCase
import com.ougi.secira.authentication.presentation.createAccessTokenJwkProvider
import com.ougi.secira.authentication.presentation.createRefreshTokenJwkProvider
import com.ougi.secira.ktorutils.getStringProperty
import io.ktor.server.application.Application
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal fun Application.createAuthenticationModule() =
    module {
        // Properties
        single(named("issuer")) { getStringProperty("jwt.issuer") }

        // Jwk providers
        single(named("access")) { createAccessTokenJwkProvider(get(named("issuer"))) }
        single(named("refresh")) { createRefreshTokenJwkProvider(get(named("issuer"))) }

        //Tokens
        single {
            TokenFactory(
                issuer = get(named("issuer")),
                accessJwkProvider = get(named("access")),
                refreshJwkProvider = get(named("refresh")),
                accessPrivateKeyText = getStringProperty("jwt.accessPrivateKey"),
                refreshPrivateKeyText = getStringProperty("jwt.refreshPrivateKey"),
            )
        }

        //JWKS
        single {
            JwksVariables(
                accessPublicKeyX = getStringProperty("jwt.accessPublicKeyX"),
                accessPublicKeyY = getStringProperty("jwt.accessPublicKeyY"),
                refreshPublicKeyX = getStringProperty("jwt.refreshPublicKeyX"),
                refreshPublicKeyY = getStringProperty("jwt.refreshPublicKeyY"),
            )
        }
        singleOf(::FillJwksFileEnvVariablesUseCase)
    }