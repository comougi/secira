package com.ougi.secira.di

import androidx.compose.runtime.Composable
import org.koin.compose.KoinMultiplatformApplication
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.KoinConfiguration

@Composable
@OptIn(KoinExperimentalAPI::class)
fun SeciraKoinApplication(content: @Composable () -> Unit) {
    KoinMultiplatformApplication(
        config = KoinConfiguration {
            allowOverride(false)
            modules(createFeaturesModule())
        },
        content = content,
    )
}