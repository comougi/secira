package com.ougi.secira.configuration.plugin

import com.ougi.secira.configuration.plugin.configurators.AndroidLibraryConfigurator
import com.ougi.secira.configuration.plugin.configurators.KotlinMultiplatformConfigurator
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("UNUSED") // Registered in local build.gradle.kts
class ConfigurationPlugin : Plugin<Project> {

    private val configurators =
        listOf(
            KotlinMultiplatformConfigurator(),
            AndroidLibraryConfigurator()
        )

    override fun apply(rootProject: Project) {
        if (rootProject != rootProject.rootProject) return
        rootProject.subprojects {
            configurators.forEach { configurator ->
                configurator.configure(this)
            }
        }
    }

}