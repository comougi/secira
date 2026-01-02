import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    jvmToolchain(25)

    iosArm64()
    iosSimulatorArm64()

    androidLibrary {
        namespace = "com.ougi.secira.userapi.domain"
        compileSdk = 36
        minSdk = 30
        compilerOptions.jvmTarget.set(JvmTarget.JVM_25)
    }
}

