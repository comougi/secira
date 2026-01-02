import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}
kotlin {
    jvmToolchain(25)
    compilerOptions.freeCompilerArgs.add("-Xcontext-parameters")

    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)
            implementation(libs.koin.compose)

            implementation(projects.userDomainImpl)
        }
    }

    androidLibrary {
        namespace = "com.ougi.secira.di"
        compileSdk = 36
        minSdk = 30
        compilerOptions.jvmTarget.set(JvmTarget.JVM_25)
    }
}