package com.cactus.pokedex.presentation.view

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import com.cactus.movie.R
import com.cactus.movie.databinding.FragmentLayoutBinding
import com.cactus.pokedex.presentation.model.PokemonVo
import com.google.android.material.math.MathUtils.lerp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContainerPokedex(
    pokedexListView: FragmentLayoutBinding,
    listPokemons: MutableState<List<PokemonVo>>
) {
    var drawerWidth by remember { mutableStateOf(1f) }


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
                DrawerValue.Open at drawerWidth * 0.7f
                DrawerValue.Closed at 0f
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onGloballyPositioned { coordinates ->
                drawerWidth = coordinates.size.width.toFloat()
            }
            .graphicsLayer {
                val offset = state.offset
                if (offset >= 0f) {
                    this.translationX = offset
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
                .background(Color(0xFFE7E7E7))
                .anchoredDraggable(state, Orientation.Horizontal)
        )
        {
            Row(modifier = Modifier
                .fillMaxSize()
                .offset(x = -(drawerWidth * .12).dp)
                .alpha(lerp(0f, 1f, state.offset / drawerWidth * 1.5f))
                .graphicsLayer {
                    this.translationX = state.offset * 0.5f
                }
            ) {
                LazyColumn(
                    modifier = Modifier
                        .weight(0.2f)
                        .padding(start = 16.dp)
                ) {
                    items(listPokemons.value) { pokemon ->

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 16.dp, top = 16.dp)
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
                Spacer(modifier = Modifier.weight(0.8f))
            }

            AndroidView(
                factory = { pokedexListView.root },
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        this.translationX = state.offset * 0.5f
                    }
                    .alpha(lerp(1f, 0f, state.offset / drawerWidth * 1.7f))
            )
        }
    }
}