package com.cactus.pokedex.presentation.model

import android.graphics.drawable.Drawable

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
