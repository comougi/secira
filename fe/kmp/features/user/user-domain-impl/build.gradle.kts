import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    jvmToolchain(25)
    compilerOptions.freeCompilerArgs.add("-Xcontext-parameters")

    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            api(projects.userDomainApi)

            implementation(libs.datastore)
            implementation(libs.koin.core)
        }
    }

    androidLibrary {
        namespace = "com.ougi.secira.userimpl.domain"
        compileSdk = 36
        minSdk = 30
        compilerOptions.jvmTarget.set(JvmTarget.JVM_25)
    }
}
