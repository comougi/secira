import java.io.File

private val dirsToExclude = listOf("**/.*", "**/includedBuild/**", "**/build/**")

collectAllModules()

private fun collectAllModules() {
    fileTree(rootDir) {
        exclude(dirsToExclude)
        include("**/build.gradle.kts")
    }
        .asSequence()
        .filter { file -> file.parentFile != rootDir }
        .map(File::getParentFile)
        .forEach { module -> includeModule(module) }
}

private fun Settings.includeModule(module: File) {
    include(":${module.name}")
    project(":${module.name}").projectDir = module
}
