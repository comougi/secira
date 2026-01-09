package com.ougi.secira.authentication.domain.model

internal class TokenPair(
    val accessToken: String,
    val refreshToken: String,
)