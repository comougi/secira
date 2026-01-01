import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvmToolchain(25)

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    )

    sourceSets {
        commonMain.dependencies {
            api(compose.material3)
        }
    }

    androidLibrary {
        namespace = "com.ougi.secira.theming.theming"
        compileSdk = 36
        minSdk = 30
        compilerOptions.jvmTarget.set(JvmTarget.JVM_25)
    }
}

