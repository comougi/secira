package com.ougi.secira.apigateway.data.db.table

import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

internal object ServicesTable : IntIdTable("gateway_services") {
    val name = varchar("service_name", 64)
}