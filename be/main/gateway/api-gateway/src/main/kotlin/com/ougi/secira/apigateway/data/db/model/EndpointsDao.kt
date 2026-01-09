package com.ougi.secira.apigateway.data.db.model

import com.ougi.secira.apigateway.data.db.table.EndpointsTable
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass

internal class EndpointsDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<EndpointsDao>(EndpointsTable)

    var serviceId by EndpointsTable.serviceId
    var path by EndpointsTable.path
    var isWhiteListed by EndpointsTable.isWhiteListedForAuth
}