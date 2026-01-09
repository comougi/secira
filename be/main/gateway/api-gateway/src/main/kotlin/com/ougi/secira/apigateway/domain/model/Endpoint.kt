package com.ougi.secira.apigateway.domain.model

internal class Endpoint(
    val serviceId: Int,
    val path: String,
    val isWhiteListedForAuth: Boolean,
)