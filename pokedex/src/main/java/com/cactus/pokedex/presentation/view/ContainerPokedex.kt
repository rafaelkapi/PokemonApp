package com.cactus.pokedex.presentation.view

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.cactus.movie.databinding.FragmentLayoutBinding
import com.google.android.material.math.MathUtils.lerp
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContainerPokedex(pokedexListView: FragmentLayoutBinding) {
    val scope = rememberCoroutineScope()
    var drawerWidth by remember { mutableStateOf(1f) }

    var drawerState by remember {
        mutableStateOf(DrawerValue.Closed)
    }

    val translationX = remember { Animatable(0f) }.also {
        it.updateBounds(drawerWidth * 0.2f, drawerWidth)
    }

    val density = LocalDensity.current
    val state = remember {
        AnchoredDraggableState(
            initialValue = DrawerValue.Closed,
            positionalThreshold = {distance: Float -> distance * 0.5f},
            animationSpec = tween(),
            velocityThreshold = { with(density) {100.dp.toPx()} }
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
    ) {
        AndroidView(factory = { pokedexListView.root },
            modifier = Modifier
                .graphicsLayer {
                    val offset = state.offset
                    if (offset >= 0f) {
                        this.translationX = offset
                        val scale = lerp(1f, 0.8f,  offset / drawerWidth)
                        this.scaleX = scale
                        this.scaleY = scale
                    }
                }
                .anchoredDraggable(state,Orientation.Horizontal)

        )
    }

    fun toggleDrawerState(){
        scope.launch {
            if (drawerState == DrawerValue.Closed) {
                translationX.animateTo(drawerWidth)
                drawerState = DrawerValue.Open
            } else {
                translationX.animateTo(0f)
                drawerState = DrawerValue.Closed
            }
        }
    }
}

