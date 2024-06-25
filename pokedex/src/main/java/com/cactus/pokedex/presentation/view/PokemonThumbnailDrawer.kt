package com.cactus.pokedex.presentation.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cactus.movie.R
import com.cactus.pokedex.presentation.model.PokemonType
import com.cactus.pokedex.presentation.model.PokemonVo

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokemonThumbnailDrawer(
    listPokemons: MutableState<List<PokemonVo>>,
    positionTop: Float,
    state: AnchoredDraggableState<DrawerValue>
) {
    var drawerWidth by remember { mutableFloatStateOf(1f) }
    val maxOffsetX = drawerWidth * 0.55f
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    val gradientTop = Brush.verticalGradient(
        colors = listOf(Color(0xFF646568), Color.Transparent),
        startY = 10f,
        endY = 100f
    )

    val gradientBottom = Brush.verticalGradient(
        colors = listOf(Color(0xFF646568), Color.Transparent),
        startY = 1700f,
        endY = 1500f
    )


    Box(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .fillMaxSize()
            .onGloballyPositioned { coordinates ->
                drawerWidth = coordinates.size.width.toFloat()
            }
        ) {
            Canvas(
                modifier = Modifier
            ) {
                drawRoundRect(
                    color = Color(0xFF646568),
                    topLeft = Offset(0f, 0f),
                    size = Size(200f, 900f),
                    cornerRadius = CornerRadius(100.dp.toPx(), 90.dp.toPx())
                )
                drawRoundRect(
                    color = Color(0xFF646568),
                    topLeft = Offset(20f, 30f),
                    size = Size(100f, 2380f),
                    cornerRadius = CornerRadius(100.dp.toPx(), 300.dp.toPx())
                )
                drawRect(
                    color = Color(0xFFE7E7E7),
                    topLeft = Offset(120f, 0f),
                    size = Size(1000f, 2380f)
                )
            }
            LazyColumn(
                modifier = Modifier
                    .width(70.dp)
                    .height((screenHeight * .8f) - positionTop.dp)
                    .offset(70.dp)

            )
            {
                items(listPokemons.value) { pokemon ->
                    Box(
                        modifier = Modifier
                            .padding(top = 40.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_pokebol),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .scale(1.7f),
                            colorFilter = ColorFilter.tint(Color.White)
                        )

                        AsyncImage(
                            model = pokemon.posterUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .alpha(alphaThumbnails(state.offset, maxOffsetX))
                                .fillMaxSize(),
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(alphaThumbnails(state.offset, maxOffsetX))
                .offset(x = 37.dp)
                .background(gradientTop)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(alphaThumbnails(state.offset, maxOffsetX))
                .offset(x = 37.dp)
                .background(gradientBottom)
        )
    }
}

private fun alphaThumbnails(offset: Float, maxOffsetX: Float): Float {
    val x = offset / maxOffsetX
    return if (offset > maxOffsetX / 2)
        2 * x - 1
    else 0f
}

@Preview(showBackground = true)
@Composable
fun PreviewPokemonThumbnailDrawer() {

    var drawerWidth by remember { mutableFloatStateOf(1f) }
    var positionTop by remember { mutableFloatStateOf(1f) }
    val screenHeight = 800.dp
    val listPokemons = listOf(
        PokemonVo(
            "#0025",
            "Pikachu",
            listOf(
                PokemonType(
                    "Electric",
                    R.drawable.ic_grass,
                    324
                ),
            ),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
        ),
        PokemonVo(
            "#0001",
            "Bulbasaur",
            listOf(
                PokemonType(
                    "Grass",
                    R.drawable.ic_grass,
                    324
                ),
                PokemonType(
                    "Poisson",
                    R.drawable.ic_grass,
                    324
                )
            ),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        ),
        PokemonVo(
            "#0025",
            "Pikachu",
            listOf(
                PokemonType(
                    "Electric",
                    R.drawable.ic_grass,
                    324
                ),
            ),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
        ),
        PokemonVo(
            "#0001",
            "Bulbasaur",
            listOf(
                PokemonType(
                    "Grass",
                    R.drawable.ic_grass,
                    324
                ),
                PokemonType(
                    "Poisson",
                    R.drawable.ic_grass,
                    324
                )
            ),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        ),
        PokemonVo(
            "#0025",
            "Pikachu",
            listOf(
                PokemonType(
                    "Electric",
                    R.drawable.ic_grass,
                    324
                ),
            ),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
        ),
        PokemonVo(
            "#0001",
            "Bulbasaur",
            listOf(
                PokemonType(
                    "Grass",
                    R.drawable.ic_grass,
                    324
                ),
                PokemonType(
                    "Poisson",
                    R.drawable.ic_grass,
                    324
                )
            ),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        ),
        PokemonVo(
            "#0025",
            "Pikachu",
            listOf(
                PokemonType(
                    "Electric",
                    R.drawable.ic_grass,
                    324
                ),
            ),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
        ),
        PokemonVo(
            "#0001",
            "Bulbasaur",
            listOf(
                PokemonType(
                    "Grass",
                    R.drawable.ic_grass,
                    324
                ),
                PokemonType(
                    "Poisson",
                    R.drawable.ic_grass,
                    324
                )
            ),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        ),
        PokemonVo(
            "#0025",
            "Pikachu",
            listOf(
                PokemonType(
                    "Electric",
                    R.drawable.ic_grass,
                    324
                ),
            ),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
        ),
        PokemonVo(
            "#0001",
            "Bulbasaur",
            listOf(
                PokemonType(
                    "Grass",
                    R.drawable.ic_grass,
                    324
                ),
                PokemonType(
                    "Poisson",
                    R.drawable.ic_grass,
                    324
                )
            ),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        ),
        PokemonVo(
            "#0025",
            "Pikachu",
            listOf(
                PokemonType(
                    "Electric",
                    R.drawable.ic_grass,
                    324
                ),
            ),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
        ),
        PokemonVo(
            "#0001",
            "Bulbasaur",
            listOf(
                PokemonType(
                    "Grass",
                    R.drawable.ic_grass,
                    324
                ),
                PokemonType(
                    "Poisson",
                    R.drawable.ic_grass,
                    324
                )
            ),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        ),
    )

    val gradientTop = Brush.verticalGradient(
        colors = listOf(Color(0xFF646568), Color.Transparent),
        startY = 10f,
        endY = 100f
    )

    val gradientBottom = Brush.verticalGradient(
        colors = listOf(Color(0xFF646568), Color.Transparent),
        startY = 1700f,
        endY = 1500f
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .fillMaxSize()
            .onGloballyPositioned { coordinates ->
                drawerWidth = coordinates.size.width.toFloat()
            }
        ) {
            Canvas(
                modifier = Modifier
            ) {
                drawRoundRect(
                    color = Color(0xFF646568),
                    topLeft = Offset(0f, 0f),
                    size = Size(200f, 900f),
                    cornerRadius = CornerRadius(100.dp.toPx(), 90.dp.toPx())
                )
                drawRoundRect(
                    color = Color(0xFF646568),
                    topLeft = Offset(20f, 30f),
                    size = Size(100f, 2380f),
                    cornerRadius = CornerRadius(100.dp.toPx(), 300.dp.toPx())
                )
                drawRect(
                    color = Color(0xFFE7E7E7),
                    topLeft = Offset(120f, 0f),
                    size = Size(1000f, 2380f)
                )
            }
            LazyColumn(
                modifier = Modifier
                    .width(70.dp)
                    .height((screenHeight * .8f) - positionTop.dp)
                    .offset(65.dp)
            )
            {
                items(listPokemons) { pokemon ->
                    Box(
                        modifier = Modifier
                            .padding(top = 40.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_pokebol),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .scale(1.7f),
                            colorFilter = ColorFilter.tint(Color.White)
                        )

                        AsyncImage(
                            model = pokemon.posterUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(x = 37.dp)
                .background(gradientTop)
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(x = 37.dp)
                .background(gradientBottom)

        )
    }
}

