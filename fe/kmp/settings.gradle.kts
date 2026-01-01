enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
apply("./common-gradle-scripts/configure-plugins-and-repos.kts")

pluginManagement {
    includeBuild("includedBuild")
}

plugins {
    id("include-all-modules")
}

rootProject.name = "secira"