package com.ougi.secira.authentication.presentation

import com.ougi.secira.authentication.domain.usecase.FillJwksFileEnvVariablesUseCase
import io.ktor.http.ContentType
import io.ktor.server.http.content.staticResources
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import org.koin.ktor.ext.inject
import java.io.File

internal fun Routing.configureJwksJsonRouting() {
    val fillJwksFileEnvVariablesUseCase by inject<FillJwksFileEnvVariablesUseCase>()
    staticResources("/", "static") {
        extensions("json")
        contentType { ContentType.Application.Json }
        modify { url, call ->
            val path = url.path
            if (path.endsWith("jwks.json")) {
                File(path)
                    .readText()
                    .run(fillJwksFileEnvVariablesUseCase::fill)
                    .let { file -> call.respond(file) }
            }
        }
    }
}