package com.ougi.secira.network.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import org.koin.dsl.module

fun createClientNetworkModule() =
    module {
        single {
            HttpClient(CIO){
                expectSuccess = false
                followRedirects = false
            }
        }
    }