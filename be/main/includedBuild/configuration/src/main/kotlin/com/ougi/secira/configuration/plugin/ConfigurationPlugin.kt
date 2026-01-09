package com.ougi.secira.configuration.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ApplicationPlugin
import org.gradle.api.plugins.JavaApplication
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper

@Suppress("UNUSED") // Registered in local build.gradle.kts
class ConfigurationPlugin : Plugin<Project> {


    override fun apply(rootProject: Project) {
        if (rootProject != rootProject.rootProject) return

        rootProject.subprojects {
            group = "com.ougi.secira"
            version = "0.0.1"
            plugins.withType(ApplicationPlugin::class.java)
                .whenPluginAdded {
                    extensions.getByType<JavaApplication>()
                        .mainClass
                        .set("io.ktor.server.netty.EngineMain")
                }
            plugins.withType(KotlinPluginWrapper::class.java)
                .whenPluginAdded {
                    extensions.getByType<KotlinJvmExtension>()
                        .compilerOptions
                        .freeCompilerArgs.add("-Xcontext-parameters")
                }
        }
    }

}