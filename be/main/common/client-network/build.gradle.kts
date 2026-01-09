plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    api(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)

    implementation(libs.ktor.server.core)

    implementation(libs.koin.ktor)
}
