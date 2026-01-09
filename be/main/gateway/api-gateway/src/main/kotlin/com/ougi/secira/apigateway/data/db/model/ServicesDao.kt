package com.ougi.secira.apigateway.data.db.model

import com.ougi.secira.apigateway.data.db.table.ServicesTable
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass

internal class ServicesDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ServicesDao>(ServicesTable)

    var name by ServicesTable.name
}