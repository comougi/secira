import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinDsl)
}

group = "com.ougi.secira"
version = "1.0"

dependencies {
    implementation(libs.pluginAsLib.kotlinJvm)
}

gradlePlugin {
    kotlin.compilerOptions.jvmTarget.set(JvmTarget.JVM_25)
    plugins {
        create("configuration") {
            id = "configuration"
            implementationClass = "com.ougi.secira.configuration.plugin.ConfigurationPlugin"
        }
    }
}