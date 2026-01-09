package com.ougi.secira.apigateway.di

import com.ougi.secira.apigateway.data.db.createDatabase
import com.ougi.secira.apigateway.data.repostiory.EndpointsRepositoryImpl
import com.ougi.secira.apigateway.domain.ServiceEndpointsRegistry
import com.ougi.secira.apigateway.domain.repostiory.EndpointsRepository
import com.ougi.secira.ktorutils.getStringProperty
import com.ougi.secira.network.di.createClientNetworkModule
import io.ktor.server.application.Application
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal fun Application.createApiGatewayModule() =
    module {
        includes(createClientNetworkModule())
        single {
            createDatabase(
                host = getStringProperty("db.host"),
                port = getStringProperty("db.port"),
                username = getStringProperty("db.username"),
                password = getStringProperty("db.password"),
            )
        }
        singleOf(::EndpointsRepositoryImpl) bind EndpointsRepository::class
        singleOf(::ServiceEndpointsRegistry)
    }