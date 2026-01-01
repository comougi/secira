package com.ougi.secira.buildrootutils

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import java.io.File

@Suppress("UNUSED") // Used in local build.gradle.kts
class IncludeAllModulesPlugin : Plugin<Settings> {

    private val dirsToExclude = listOf("includedBuild", "build")

    override fun apply(settings: Settings) {
        settings.rootDir
            .walk()
            .filterNot(File::isHidden)
            .filterNot { file ->
                dirsToExclude.any { dirName ->
                    file.path.contains("/$dirName/")
                }
            }
            .filter { file -> file.name == "build.gradle.kts" }
            .filter { file -> file.parentFile != settings.rootDir }
            .map(File::getParentFile)
            .forEach { module ->
                with(settings) {
                    include(":${module.name}")
                    project(":${module.name}").projectDir = module
                }
            }
    }

}