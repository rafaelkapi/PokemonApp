rootProject.name = "PokemonApp"

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}
include(
    ":app",
    ":pokedex",
    ":commons:network",
    ":commons:base",
    ":commons:extensions",
)
