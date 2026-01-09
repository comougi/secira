package com.ougi.secira.authentication

import com.ougi.secira.authentication.di.configureDi
import com.ougi.secira.authentication.presentation.configurePlugins
import com.ougi.secira.authentication.presentation.configureRouting
import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    configureDi()
    configurePlugins()
    configureRouting()
}
