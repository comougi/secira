plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    iosArm64()
    iosSimulatorArm64()
    androidLibrary.namespace = "com.ougi.secira.userimpl.domain"

    sourceSets {
        commonMain.dependencies {
            api(projects.userDomainApi)

            implementation(libs.datastore)
            implementation(libs.koin.core)
        }
    }
}
