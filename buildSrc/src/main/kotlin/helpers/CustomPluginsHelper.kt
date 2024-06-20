package helpers

import core.*
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import projects.model.ModuleType
import projects.model.ProjectInfo
import plugins.AndroidAppBuildLogicPlugin
import plugins.AndroidLibraryBuildLogicPlugin
import plugins.AndroidSampleBuildLogicPlugin
import plugins.JavaLibraryBuildLogicPlugin

/**
 * Helper method to manage modules configuration based on [ModuleType] object information
 *
 * @param projectInfo - [ProjectInfo] object containing all information for that project
 */
fun Project.applyCustomPluginsByModuleType(projectInfo: ProjectInfo) {
    projectInfo.modules.firstOrNull {
        it.path.trim() == project.project.path.trim()
    }?.let { module ->

        when (module.type) {
            ModuleType.ANDROID_APPLICATION -> project.plugins.apply(AndroidAppBuildLogicPlugin::class.java)
            ModuleType.ANDROID_SAMPLE -> project.plugins.apply(AndroidSampleBuildLogicPlugin::class.java)
            ModuleType.JAVA_LIBRARY -> project.plugins.apply(JavaLibraryBuildLogicPlugin::class.java)
            ModuleType.ANDROID_LIBRARY -> project.plugins.apply(AndroidLibraryBuildLogicPlugin::class.java)
        }

        if (module.type != ModuleType.JAVA_LIBRARY) {
            this.setupAndroidSdkVersions(projectInfo)
        }
    }
}