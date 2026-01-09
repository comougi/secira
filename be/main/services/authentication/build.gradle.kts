plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinSerialization)
}

dependencies {
    implementation(project(":ktor-utils"))
    implementation(project(":client-network"))

    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.config.yaml)

    implementation(libs.koin.ktor)

    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.authJwt)
    implementation(libs.ktor.server.serialization)
    implementation(libs.ktor.server.contentNegotiation)

    implementation(libs.logback.classic)
}
