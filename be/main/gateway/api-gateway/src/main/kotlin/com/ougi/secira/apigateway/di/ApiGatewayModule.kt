package com.ougi.secira.apigateway.di

import com.ougi.secira.apigateway.data.db.createDatabase
import com.ougi.secira.apigateway.data.repostiory.EndpointsRepositoryImpl
import com.ougi.secira.apigateway.domain.ServiceEndpointsRegistry
import com.ougi.secira.apigateway.domain.repostiory.EndpointsRepository
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
                host = environment.config.property("db.host").getString(),
                port = environment.config.property("db.port").getString(),
                username = environment.config.property("db.username").getString(),
                password = environment.config.property("db.password").getString(),
            )
        }
        singleOf(::EndpointsRepositoryImpl) bind EndpointsRepository::class
        singleOf(::ServiceEndpointsRegistry)
    }