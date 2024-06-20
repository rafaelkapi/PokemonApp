package projects.library

import projects.model.Module
import projects.model.ModuleType
import projects.model.ProjectInfo

object Pokedex {
    val projectInfo = ProjectInfo(
        name = Modules.ROOT,
        applicationId = "com.cactus.android.sample",
        groupId = "com.cactus.pokedex",
        scheme = "appPokemon",
        
        modules = listOf(
            Module(
                path = Modules.ROOT,
                type = ModuleType.ANDROID_LIBRARY,
            ),
        )
    )

    object Modules {
        const val ROOT = ":pokedex"
    }
}