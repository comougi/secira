package com.ougi.secira.ktorutils

import io.ktor.server.application.Application

fun Application.getStringProperty(name: String) =
    environment.config.property(name).getString()