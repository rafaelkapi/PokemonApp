package core

import com.android.build.gradle.BaseExtension
import org.gradle.api.GradleException
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import projects.model.ProjectInfo

/**
 * Detect if the module is an Android module
 */
fun Project.android(): BaseExtension {
    return project.extensions.findByType(BaseExtension::class.java)
        ?: throw GradleException("Project $name is not an Android project")
}

/**
 * Setup commons Android options and configurations
 */
fun Project.setupCommonsOptions() {
    android().apply {
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        testOptions {
            unitTests.isIncludeAndroidResources = true
            unitTests.isReturnDefaultValues = true
        }

        lintOptions {
            isAbortOnError = false
            isCheckAllWarnings = true
            isWarningsAsErrors = true
            isExplainIssues = true

        }

        sourceSets {
            getByName("main").java.srcDirs("src/main/kotlin")
            getByName("test").java.srcDirs("src/test/kotlin")
            getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
        }
    }
}

/**
 * Setup min, compile and target SDK version for module
 *
 * @param projectInfo - [ProjectInfo] object containing all the project information
 */
fun Project.setupAndroidSdkVersions(projectInfo: ProjectInfo) {
    android().apply {
        compileSdkVersion = projectInfo.versions.compileSdkVersion

        defaultConfig {
            targetSdk = projectInfo.versions.targetSdk
            minSdk = projectInfo.versions.minSdkVersion

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            vectorDrawables.useSupportLibrary = true
        }

        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
                consumerProguardFiles("proguard-rules.pro")
                matchingFallbacks += listOf("release")
            }
            getByName("debug") {
                isMinifyEnabled = false
                matchingFallbacks += listOf("debug")
            }
        }
    }
}
