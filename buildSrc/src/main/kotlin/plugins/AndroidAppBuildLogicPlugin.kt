package plugins

import core.applyAndroidCommonsPlugins
import core.setupCommonsOptions
import core.setupKotlinConfig
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Perform basic configuration for an app module
 */
abstract class AndroidAppBuildLogicPlugin: Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        applyPlugins()
        setupCommonsOptions()
        setupKotlinConfig()
    }

    private fun Project.applyPlugins() {
        plugins.apply("com.android.application")
        applyAndroidCommonsPlugins()
    }
}