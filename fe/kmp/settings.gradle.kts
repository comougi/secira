enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
apply("./common-gradle-scripts/configure-plugins-and-repos.kts")
apply("./common-gradle-scripts/collect-modules.gradle.kts")

rootProject.name = "secira"

includeBuild("includedBuild")