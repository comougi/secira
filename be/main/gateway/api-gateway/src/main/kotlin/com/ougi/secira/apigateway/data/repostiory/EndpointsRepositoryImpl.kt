package com.ougi.secira.apigateway.data.repostiory

import com.ougi.secira.apigateway.data.db.model.EndpointsDao
import com.ougi.secira.apigateway.data.db.model.ServicesDao
import com.ougi.secira.apigateway.data.mapping.toDomain
import com.ougi.secira.apigateway.domain.model.Endpoint
import com.ougi.secira.apigateway.domain.model.Service
import com.ougi.secira.apigateway.domain.repostiory.EndpointsRepository
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.JdbcTransaction
import org.jetbrains.exposed.v1.jdbc.transactions.suspendTransaction

internal class EndpointsRepositoryImpl(
    private val database: Database,
) : EndpointsRepository {

    override suspend fun getAllServices(): List<Service> =
        databaseTransaction {
            ServicesDao
                .all()
                .map(ServicesDao::toDomain)
        }

    override suspend fun getAllEndpoints(): List<Endpoint> =
        databaseTransaction {
            EndpointsDao
                .all()
                .map(EndpointsDao::toDomain)
        }

    private suspend fun <T> databaseTransaction(transaction: JdbcTransaction.() -> T): T =
        suspendTransaction(
            db = database,
            statement = transaction
        )
}