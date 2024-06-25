package com.cactus.pokedex.presentation.view

import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.cactus.movie.databinding.FragmentLayoutBinding
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
    val maxOffsetX = drawerWidth * 0.55f

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
                    this.translationY = offset * 1.2f
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

            PokemonThumbnailDrawer(
                listPokemons = listPokemons,
                positionTop = positionTop,
                state = state
            )

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

fun alphaPokemonList(offset: Float, drawerWidth: Float): Float {
    val x = offset / drawerWidth
    val offsetXWidth = .50f
    return -(1 / offsetXWidth) * x + 1
}

