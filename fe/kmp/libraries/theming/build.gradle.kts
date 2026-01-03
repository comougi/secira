plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    iosArm64()
    iosSimulatorArm64()
    androidLibrary.namespace = "com.ougi.secira.theming.theming"

    sourceSets {
        commonMain.dependencies {
            api(compose.material3)
        }
    }
}