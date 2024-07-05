package com.cactus.pokedex.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.cactus.movie.R
import com.cactus.pokedex.presentation.model.PokemonDetailVo
import com.cactus.pokedex.presentation.model.PokemonStatsVo
import com.cactus.pokedex.presentation.view.widgets.PokemonEvolution
import com.cactus.pokedex.presentation.view.widgets.PokemonImage
import com.cactus.pokedex.presentation.view.widgets.PokemonStats

@Composable
fun PokemonDetail(detailVo: PokemonDetailVo) {
    val offsetYGeometricBackground = -80.dp
    val offsetXGeometricBackground = -30.dp

    ConstraintLayout(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.navigationBars)) {
        val (pokemonImage, pokemonEvolution, pokemonStats) = createRefs()

        PokemonImage(
            Modifier.constrainAs(pokemonImage) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            detailVo.posterUrl
        )

        Image(
            painter = painterResource(id = R.drawable.geometric_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .offset(offsetXGeometricBackground, offsetYGeometricBackground)
                .alpha(0.1f)
        )

        PokemonEvolution(
            Modifier
                .constrainAs(pokemonEvolution) {
                    top.linkTo(pokemonImage.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .offset(y = -10.dp),
            detailVo.evolutionsThumbnails,
        )

        PokemonStats(
            Modifier
                .fillMaxWidth(0.7f)
                .padding(start = 4.dp, bottom = 8.dp, end = 4.dp)
                .constrainAs(pokemonStats) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                },
            detailVo.stats
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PokemonDetailPreview() {

    val stats = PokemonStatsVo(
        hp = "HP" to 0.3f,
        attack = "Attack" to 0.55f,
        defense = "Defense" to 0.7f,
        specialAttack = 0.7f,
        specialDefense = 0.8f,
        speed = "Speed" to 0.5f
    )

    val detailVo = PokemonDetailVo(
        "#0025",
        "Pikachu",
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/25.png",
        listOf(
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/172.png",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/26.png",
        ),
        stats
    )
    PokemonDetail(detailVo)
}