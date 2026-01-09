package com.ougi.secira.authentication.presentation

import com.auth0.jwk.JwkProvider
import com.auth0.jwk.JwkProviderBuilder
import org.koin.core.scope.Scope
import java.util.concurrent.TimeUnit

context(_: Scope)
internal fun createAccessTokenJwkProvider(issuer: String): JwkProvider =
    JwkProviderBuilder(issuer)
        .rateLimited(2, 1, TimeUnit.MINUTES)
        .build()

context(_: Scope)
internal fun createRefreshTokenJwkProvider(issuer: String): JwkProvider =
    JwkProviderBuilder(issuer)
        .cached(100, 5, TimeUnit.MINUTES)
        .rateLimited(5, 1, TimeUnit.MINUTES)
        .build()