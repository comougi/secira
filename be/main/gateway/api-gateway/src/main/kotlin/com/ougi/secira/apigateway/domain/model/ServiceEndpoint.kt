package com.ougi.secira.apigateway.domain.model

internal class ServiceEndpoint(
    val serviceName: String,
    val path: String,
    val isWhiteListedForAuth: Boolean,
)