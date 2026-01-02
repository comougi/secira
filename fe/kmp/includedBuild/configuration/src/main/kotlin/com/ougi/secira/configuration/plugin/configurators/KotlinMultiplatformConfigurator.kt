package com.ougi.secira.configuration.plugin.configurators

import com.ougi.secira.configuration.plugin.Configurator
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinMultiplatformPluginWrapper

internal class KotlinMultiplatformConfigurator : Configurator {
    override fun configure(project: Project) {
        with(project) {
            plugins.withType(KotlinMultiplatformPluginWrapper::class) {
                extensions.getByType<KotlinMultiplatformExtension>()
                    .apply {
                        jvmToolchain(25)
                        compilerOptions.freeCompilerArgs.add("-Xcontext-parameters")
                    }
            }
        }
    }
}