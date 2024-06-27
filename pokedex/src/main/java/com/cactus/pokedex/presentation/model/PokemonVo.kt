package com.cactus.pokedex.presentation.model

data class PokemonVo(
    val id: String,
    val name: String,
    val types: List<PokemonType>,
    val posterUrl: String,
)

data class PokemonType(
    val type: String,
    val icon: Int,
    val color: Int
)
