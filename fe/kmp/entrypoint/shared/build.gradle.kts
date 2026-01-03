plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "entryPointShared"
            isStatic = true
        }
    }
    androidLibrary.namespace = "com.ougi.secira.entrypoint"

    sourceSets {
        commonMain.dependencies {
            implementation(projects.di)
            implementation(projects.theming)
        }
    }
}