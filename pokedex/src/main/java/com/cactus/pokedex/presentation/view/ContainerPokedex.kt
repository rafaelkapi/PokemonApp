package com.cactus.pokedex.presentation.view

import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import com.cactus.movie.R
import com.cactus.movie.databinding.FragmentLayoutBinding
import com.cactus.pokedex.presentation.model.PokemonType
import com.cactus.pokedex.presentation.model.PokemonVo
import com.google.android.material.math.MathUtils.lerp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContainerPokedex(
    pokedexListView: FragmentLayoutBinding,
    listPokemons: MutableState<List<PokemonVo>>
) {
    var drawerWidth by remember { mutableFloatStateOf(1f) }
    var positionTop by remember { mutableFloatStateOf(1f) }
    val maxOffsetX = drawerWidth * 0.6f

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val density = LocalDensity.current
    val state = remember {
        AnchoredDraggableState(
            initialValue = DrawerValue.Closed,
            positionalThreshold = { distance: Float -> distance * 0.5f },
            animationSpec = spring(),
            velocityThreshold = { with(density) { 100.dp.toPx() } }
        )
    }.apply {
        updateAnchors(
            DraggableAnchors {
                DrawerValue.Open at maxOffsetX
                DrawerValue.Closed at 0f
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onGloballyPositioned { coordinates ->
                drawerWidth = coordinates.size.width.toFloat()
                positionTop = coordinates.positionInRoot().x
            }
            .graphicsLayer {
                val offset = state.offset
                if (offset >= 0f) {
                    this.translationX = offset
                    this.translationY = offset * 1.1f
                    val scale = lerp(1f, 0.8f, offset / drawerWidth)
                    this.scaleX = scale
                    this.scaleY = scale
                }
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))

                .anchoredDraggable(state, Orientation.Horizontal)
        )
        {
            val gradientTop = Brush.verticalGradient(
                colors = listOf(Color(0xFF646568), Color.Transparent),
                startY = 10f,
                endY = 200f
            )
            val gradienBottom = Brush.verticalGradient(
                colors = listOf(Color(0xFF646568), Color.Transparent),
                startY = 1700f,
                endY = 1450f
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
                            .height((screenHeight * .73f) - positionTop.dp)
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
                                    modifier = Modifier.fillMaxSize(),
                                    colorFilter = ColorFilter.tint(Color.Black)
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
                        .background(gradienBottom)

                )

            }

            AndroidView(
                factory = { pokedexListView.root },
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        this.translationX = state.offset * 0.8f
                    }
                    .background(Color(0xFFE7E7E7))
                    .alpha(alphaPokemonList(state.offset, drawerWidth))
            )
        }
    }
}

private fun alphaThumbnails(offset: Float, maxOffsetX: Float): Float {
    val x = offset / maxOffsetX
    return if (offset > maxOffsetX / 2)
        2 * x - 1
    else 0f
}

private fun alphaPokemonList(offset: Float, drawerWidth: Float): Float {
    val x = offset / drawerWidth
    val offsetXWidth = .50f
    return -(1 / offsetXWidth) * x + 1
}

@Preview(showBackground = true)
@Composable
fun PreviewContainerPokedex() {

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
        endY = 200f
    )
    val gradienBottom = Brush.verticalGradient(
        colors = listOf(Color(0xFF646568), Color.Transparent),
        startY = 1700f,
        endY = 1400f
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
                    .height((screenHeight * .73f) - positionTop.dp)
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
                            colorFilter = ColorFilter.tint(Color.Black)
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
                .background(gradienBottom)

        )
    }
}