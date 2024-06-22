import helpers.applyCustomPluginsByModuleType
subprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        google()
        gradlePluginPortal()
        maven("https://jitpack.io")
    }

    // Plugins Apps
    this.applyCustomPluginsByModuleType(projects.app.PokedexApp.projectInfo)

    // Plugins Libraries
    this.applyCustomPluginsByModuleType(projects.library.Pokedex.projectInfo)
    this.applyCustomPluginsByModuleType(projects.library.Commons.projectInfo)

    plugins.withId("com.android.application") {

        configure<com.android.build.gradle.BaseExtension> {
            signingConfigs {
                create("release") {
                    isV2SigningEnabled = true
                }
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }
            tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
                kotlinOptions.jvmTarget = "17"
            }
            defaultConfig {
                manifestPlaceholders["scheme_app"] = "pokemonapp"
                versionCode = 1
                versionName = "0.0.1"
            }
            buildTypes {
                getByName("release") {
                    signingConfig = signingConfigs.getByName("release")
                    resValue("string", "app_name", "Pokemon")
                }

                getByName("debug") {
                    if (project.project.path.trim().endsWith("app")) {
                        applicationIdSuffix = ".hml"
                    }
                    resValue("string", "app_name", "Pokemon")
                }
            }
        }
    }
}

