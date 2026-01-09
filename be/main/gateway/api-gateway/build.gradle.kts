plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
}

kotlin.compilerOptions.freeCompilerArgs.add("-XXLanguage:+ExplicitBackingFields")

dependencies {
    implementation(project(":client-network"))

    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.config.yaml)
    implementation(libs.ktor.server.forwarding)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)

    implementation(libs.koin.ktor)

    implementation(libs.exposed.core)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.dao)
    implementation(libs.postgresql)
    implementation(libs.h2)

    implementation(libs.logback.classic)
}
