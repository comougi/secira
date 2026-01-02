package com.ougi.secira.configuration.plugin.configurators

import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import com.android.build.gradle.internal.plugins.KotlinMultiplatformAndroidPlugin
import com.ougi.secira.configuration.plugin.Configurator
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal class AndroidLibraryConfigurator : Configurator {
    override fun configure(project: Project) {
        with(project) {
            plugins.withType(KotlinMultiplatformAndroidPlugin::class) {
                extensions.getByType<KotlinMultiplatformExtension>()
                    .extensions
                    .getByType<KotlinMultiplatformAndroidLibraryTarget>()
                    .apply {
                        compileSdk = 36
                        minSdk = 30
                        compilerOptions.jvmTarget.set(JvmTarget.JVM_25)
                    }

            }
        }
    }
}