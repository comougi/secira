package com.ougi.secira.di

import com.ougi.serica.useriimpl.domain.di.UserDomainApiProvider
import org.koin.dsl.module

internal fun createFeaturesModule() =
    module {
        single { UserDomainApiProvider.provideUserApi() }
    }