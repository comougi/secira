package com.ougi.secira.configuration.plugin

import org.gradle.api.Project

internal interface Configurator {

    fun configure(project: Project)

}