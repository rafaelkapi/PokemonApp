package projects.library

import projects.model.Module
import projects.model.ModuleType
import projects.model.ProjectInfo

object Commons {
    val projectInfo = ProjectInfo(
        name = Modules.ROOT,
        applicationId = "com.cactus.android.sample",
        groupId = "com.cactus.commons",
        scheme = "appthemovie",
        
        modules = listOf(
            Module(
                path = Modules.network,
                type = ModuleType.ANDROID_LIBRARY,
            ),
            Module(
                path = Modules.base,
                type = ModuleType.ANDROID_LIBRARY,
            ),
            Module(
                path = Modules.extensions,
                type = ModuleType.ANDROID_LIBRARY,
            )
        )
    )

    object Modules {
        const val ROOT = ":commons"
        const val network = "$ROOT:network"
        const val base = "$ROOT:base"
        const val extensions = "$ROOT:extensions"
    }
}