package com.ougi.secira.apigateway.data.db

import org.jetbrains.exposed.v1.jdbc.Database
import org.koin.core.scope.Scope

context(_: Scope)
internal fun createDatabase(
    host: String,
    port: String,
    username: String,
    password: String,
) =
    Database.connect(
        url = "jdbc:postgresql://$host:$port/utils_db",
        driver = "org.postgresql.Driver",
        user = username,
        password = password,
    )