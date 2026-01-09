package com.ougi.secira.authentication.presentation.model

import kotlinx.serialization.Serializable

@Serializable
class TokenPairResponse(
    val accessToken: String,
    val refreshToken: String,
)