package com.ougi.secira.apigateway.data.mapping

import com.ougi.secira.apigateway.data.db.model.EndpointsDao
import com.ougi.secira.apigateway.domain.model.Endpoint

internal fun EndpointsDao.toDomain() =
    Endpoint(
        serviceId = serviceId,
        path = "/$path",
        isWhiteListedForAuth = isWhiteListed,
    )