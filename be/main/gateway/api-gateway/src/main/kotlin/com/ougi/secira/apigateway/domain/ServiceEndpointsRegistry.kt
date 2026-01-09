package com.ougi.secira.apigateway.domain

import com.ougi.secira.apigateway.domain.model.Endpoint
import com.ougi.secira.apigateway.domain.model.Service
import com.ougi.secira.apigateway.domain.model.ServiceEndpoint
import com.ougi.secira.apigateway.domain.repostiory.EndpointsRepository
import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.minutes

internal class ServiceEndpointsRegistry(
    private val endpointsRepository: EndpointsRepository,
) {

    @Volatile
    var serviceEndpointsCache: HashMap<String, ServiceEndpoint> = HashMap()
        private set

    context(scope: CoroutineScope)
    fun startCacheUpdating() {
        scope.launch(Dispatchers.IO) {
            while (isActive) {
                val services = endpointsRepository.getAllServices()
                val endpoints = endpointsRepository.getAllEndpoints()
                val serviceEndpoints = createServiceEndpoints(services, endpoints)
                serviceEndpointsCache = serviceEndpoints.associateByTo(HashMap()) { it.path }
                delay(5.minutes)
            }
        }
    }

    private fun createServiceEndpoints(
        services: List<Service>,
        endpoints: List<Endpoint>,
    ): List<ServiceEndpoint> {
        val serviceNamesById = services.associateBy({ it.id }, { it.name })
        return endpoints.mapNotNull { endpoint ->
            serviceNamesById[endpoint.serviceId]
                ?.let { serviceName ->
                    ServiceEndpoint(
                        serviceName = serviceName,
                        path = endpoint.path,
                        isWhiteListedForAuth = endpoint.isWhiteListedForAuth,
                    )
                }
        }
    }
}