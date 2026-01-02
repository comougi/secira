package com.ougi.serica.useriimpl.domain.di

import com.ougi.serica.useriapi.domain.UserDomainApi
import com.ougi.serica.useriimpl.domain.UserDomainImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal fun createUserDomainApiModule() =
    module {
        factoryOf<UserDomainApi>(::UserDomainImpl)
    }