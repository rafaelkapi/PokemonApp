package com.cactus.pokedex.presentation.view

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
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
import androidx.compose.ui.viewinterop.AndroidView
import com.cactus.movie.databinding.FragmentLayoutBinding
import com.google.android.material.math.MathUtils.lerp
import kotlinx.coroutines.launch

@Composable
fun ContainerPokedex(pokedexListView: FragmentLayoutBinding) {
    val scope = rememberCoroutineScope()
    var drawerWidth by remember { mutableStateOf(1f) }

    var drawerState by remember {
        mutableStateOf(DrawerValue.Closed)
    }

    val translationX = remember { Animatable(0f) }.also {
        it.updateBounds(0f, drawerWidth)
    }

    val draggableState = rememberDraggableState(onDelta = { dragAmount ->
        scope.launch {
            translationX.snapTo(translationX.value + dragAmount)
        }
    })

    val decay = rememberSplineBasedDecay<Float>()

//    val anchor = DraggableAnchors {}

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
                    this.translationX = translationX.value
                    val scale = lerp(1f, 0.8f, translationX.value / drawerWidth)
                    this.scaleX = scale
                    this.scaleY = scale
                }
                .draggable(
                    state = draggableState,
                    orientation = Orientation.Horizontal,
                    onDragStopped = { velocity ->
                        val decayX = decay.calculateTargetValue(
                            translationX.value,
                            velocity,
                        )
                        scope.launch {
                            val targetX = if (decayX > drawerWidth * 0.5) drawerWidth
                            else 0f

                            val canReachTargetWithDecay =
                                (decayX > targetX && targetX == drawerWidth)
                                        || (decayX < targetX && targetX == 0f)

                            if (canReachTargetWithDecay) {
                                translationX.animateDecay(
                                    initialVelocity = velocity,
                                    animationSpec = decay
                                )
                            } else {
                                translationX.animateTo(targetX, initialVelocity = velocity)
                            }
                            drawerState = if (targetX == drawerWidth) DrawerValue.Open
                            else DrawerValue.Closed
                        }
                    }
                )
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

