package com.ougi.secira.apigateway

import com.ougi.secira.apigateway.di.configureDi
import com.ougi.secira.apigateway.presentation.configurePlanning
import com.ougi.secira.apigateway.presentation.configurePlugins
import com.ougi.secira.apigateway.presentation.configureRouting
import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    configureDi()
    configurePlugins()
    configureRouting()
    configurePlanning()
}
