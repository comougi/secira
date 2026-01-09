package com.ougi.secira.apigateway.data.db.table

import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

internal object EndpointsTable : IntIdTable("gateway_endpoints") {
    val serviceId = integer("service_id")
    val path = varchar("endpoint_path", 64)
    val isWhiteListedForAuth = bool("auth_white_listed")
}