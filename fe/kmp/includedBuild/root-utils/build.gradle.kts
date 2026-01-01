import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinDsl)
}

group = "com.ougi.secira"
version = "1.0"

gradlePlugin {
    kotlin.compilerOptions.jvmTarget.set(JvmTarget.JVM_25)
    plugins {
        create("include-all-modules") {
            id = "include-all-modules"
            implementationClass = "com.ougi.secira.buildrootutils.IncludeAllModulesPlugin"
        }
    }
}