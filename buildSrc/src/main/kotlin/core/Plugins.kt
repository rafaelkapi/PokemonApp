package core

import org.gradle.api.Project

/**
 * Apply commons Android plugins to the module
 */
fun Project.applyAndroidCommonsPlugins() {
    plugins.run {
        apply("kotlin-android")
        apply("kotlin-kapt")
    }
}