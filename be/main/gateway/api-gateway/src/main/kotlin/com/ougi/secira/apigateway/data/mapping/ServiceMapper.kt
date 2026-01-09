package com.ougi.secira.apigateway.data.mapping

import com.ougi.secira.apigateway.data.db.model.ServicesDao
import com.ougi.secira.apigateway.domain.model.Service

internal fun ServicesDao.toDomain() =
    Service(
        id = id.value,
        name = name,
    )