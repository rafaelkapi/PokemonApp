package plugins

import core.*
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Perform basic configuration for an Android module
 */
abstract class AndroidLibraryBuildLogicPlugin: Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        applyPlugins()
        setupCommonsOptions()
        setupKotlinConfig()
    }

    private fun Project.applyPlugins() {
        plugins.apply("com.android.library")
        applyAndroidCommonsPlugins()
    }
}