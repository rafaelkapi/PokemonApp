package projects.library

import projects.model.Module
import projects.model.ModuleType
import projects.model.ProjectInfo

object TheMovie {
    val projectInfo = ProjectInfo(
        name = Modules.ROOT,
        applicationId = "com.cactus.android.sample",
        groupId = "com.cactus.commons",
        scheme = "appthemovie",
        
        modules = listOf(
            Module(
                path = Modules.ROOT,
                type = ModuleType.ANDROID_LIBRARY,
            ),
            Module(
                path = Modules.movie,
                type = ModuleType.ANDROID_LIBRARY,
            )
        )
    )

    object Modules {
        const val ROOT = ":TheMovie"
        val movie = ":movie"
    }
}