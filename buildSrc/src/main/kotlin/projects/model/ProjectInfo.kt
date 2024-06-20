package projects.model

/**
 * Contain all the project information used for configuration
 *
 * @property name - the name of the domain, is the same name of the project folder
 * @property applicationId - the name of the sample package, usually the same name in the sample AndroidManifest.xml
 * @property groupId - group id used for the artifact generation for all modules in that project
 * @property scheme - the app scheme, generally used for deeplinks
 * @property versions - a [AndroidVersion] object containing Android modules specific configurations
 * @property modules - list of [Module] objects with all modules inside that project
 **/
data class ProjectInfo(
    val name: String,
    val applicationId: String,
    val groupId: String,
    val scheme: String,
    val versions: AndroidVersion = AndroidVersion(),
    val modules: List<Module> = emptyList(),
)

/**
 * Contain specific configurations for Android modules
 *
 * @property compileSdkVersion - the compile SDK version for an Android module
 * @property minSdkVersion - the min SDK version for an Android module
 * @property targetSdk - the target SDK version for an Android module
 */
data class AndroidVersion(
    val compileSdkVersion: String = "android-34",
    val minSdkVersion: Int = 21,
    val targetSdk: Int = 34
)

/**
 * Contains the module information for basic setup
 *
 * @property path - gradle path for the module (ex: ':soma:alerts')
 * @property type - a [ModuleType] object setting the type of the module to configure correctly
 */
data class Module(
    val path: String,
    val type: ModuleType,
)


/**
 * Constants for default modules names
 */
object DefaultModuleNames {
    const val APP = "app"
    const val SAMPLE = "sample"
}

/**
 * The type of the module, used for setting up the correct plugins and specific configurations
 */
enum class ModuleType(val Value: String) {
    ANDROID_APPLICATION("app"),
    ANDROID_SAMPLE("sample"),
    ANDROID_LIBRARY("android_library"),
    JAVA_LIBRARY("java_library")
}