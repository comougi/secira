package com.ougi.secira.apigateway.domain.repostiory

import com.ougi.secira.apigateway.domain.model.Endpoint
import com.ougi.secira.apigateway.domain.model.Service

internal interface EndpointsRepository {

    suspend fun getAllServices(): List<Service>

    suspend fun getAllEndpoints(): List<Endpoint>

}