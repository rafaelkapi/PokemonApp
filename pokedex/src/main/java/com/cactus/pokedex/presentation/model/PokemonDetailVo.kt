package com.cactus.pokedex.presentation.model

data class PokemonDetailVo(
    val id: String,
    val name: String,
    val posterUrl: String,
    val drawableBackground: Int,
    val evolutionsThumbnails: List<String>,

    val spec: PokemonSpecVo,
    val stats: PokemonStatsVo,
)

data class PokemonStatsVo(
    val hp: Pair<String, Float>,
    val attack: Pair<String, Float>,
    val defense: Pair<String, Float>,
    val specialAttack: Float?,
    val specialDefense: Float?,
    val speed: Pair<String, Float>
)

data class PokemonSpecVo(
    val height : String,
    val weight : String,
    val category : String,
    val type: List<Pair<Int, String>>,
    val weaknesses: List<Pair<Int, String>>,
)