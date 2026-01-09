package com.ougi.secira.authentication.presentation.model

import kotlinx.serialization.Serializable

@Serializable
class CreateTokensRequest(
    val username: String,
)