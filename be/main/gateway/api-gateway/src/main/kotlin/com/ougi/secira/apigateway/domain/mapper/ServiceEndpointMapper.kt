package com.ougi.secira.apigateway.domain.mapper

import com.ougi.secira.apigateway.domain.model.Endpoint
import com.ougi.secira.apigateway.domain.model.ServiceEndpoint

internal fun Endpoint.toServiceEndpoint(
    serviceName: String,
) =
    ServiceEndpoint(
        serviceName = serviceName,
        path = path,
        isWhiteListedForAuth = isWhiteListedForAuth,
    )