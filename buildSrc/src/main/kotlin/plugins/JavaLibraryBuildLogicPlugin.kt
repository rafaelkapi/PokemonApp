package plugins

import org.gradle.api.GradleException
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension

/**
 * Perform basic configuration for Java module
 */
abstract class JavaLibraryBuildLogicPlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        applyPlugins()
        setupCommonsJavaOptions()
    }

    private fun Project.applyPlugins() {
        plugins.apply("java-library")
        plugins.apply("org.jetbrains.kotlin.jvm")
    }

    private fun Project.java(): JavaPluginExtension {
        return project.extensions.findByType(JavaPluginExtension::class.java)
            ?: throw GradleException("Project $name is not a Java project")
    }

    private fun Project.setupCommonsJavaOptions() {
        java().apply {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }
}