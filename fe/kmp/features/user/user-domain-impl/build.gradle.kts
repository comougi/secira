plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
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
    }
}
