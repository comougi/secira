import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinDsl)
}

dependencies {
    implementation(libs.pluginAsLib.multiplatform)
    implementation(libs.pluginAsLib.multiplatformAndroidLibrary)
}

group = "com.ougi.secira"
version = "1.0"

gradlePlugin {
    kotlin.compilerOptions.jvmTarget.set(JvmTarget.JVM_25)
    plugins {
        create("configuration") {
            id = "configuration"
            implementationClass = "com.ougi.secira.configuration.plugin.ConfigurationPlugin"
        }
    }
}