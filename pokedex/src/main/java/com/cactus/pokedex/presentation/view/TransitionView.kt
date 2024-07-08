package com.cactus.pokedex.presentation.view

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cactus.commons.livedata.ViewState
import com.cactus.commons.livedata.ViewState.Loading
import com.cactus.commons.livedata.ViewState.ErrorValue
import com.cactus.movie.R
import kotlinx.coroutines.delay

@Composable
fun TransitionView(state: ViewState, onClickRetry: () -> Unit = {}) {

    val gradient = Brush.verticalGradient(
        colors = listOf(Color.Black, Color.Transparent),
        startY = 600f,
        endY = 0f
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.7f)
            .background(Color.Black)
            .clickable(onClick = (state as? ErrorValue<*>)?.let { onClickRetry } ?: { Unit })
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .alpha(0.6f)
                    .background(gradient)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .rotate(180f)
                    .weight(1f)
                    .alpha(0.6f)
                    .background(gradient)
            )
        }


        when (state) {
            is Loading -> RotatingDrawableAnimation()
            is ErrorValue<*> -> (state.error as? String)?.let { ErrorState(it) }
            else -> Unit
        }
    }
}

@Composable
fun ErrorState(message: String) {
    Column(
        modifier = Modifier.fillMaxWidth(.6f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message, color = Color(0xFFB1B1B4), style = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                lineHeight = 30.sp,
                letterSpacing = 0.sp
            )
        )
        Spacer(modifier = Modifier.height(30.dp))

        Image(
            painter = painterResource(id = R.drawable.outline_autorenew_24),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(60.dp),
        )
    }
}

@Composable
fun RotatingDrawableAnimation() {
    var rotation = remember { mutableStateOf(0f) }
    val animatedRotation = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        while (true) {
            animatedRotation.animateTo(
                targetValue = 360f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = 100f
                ),
                block = {
                    rotation.value = animatedRotation.value
                }
            )

            delay(500)

            animatedRotation.snapTo(0f)
        }
    }

    val animatedAlpha = remember { Animatable(0.5f) }

    LaunchedEffect(key1 = true) {
        while (true) {
            animatedAlpha.animateTo(
                targetValue = .7f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = 100f
                ),
            )
            animatedAlpha.animateTo(
                targetValue = 0.5f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = 100f
                ),
            )
        }
    }

    val scale = remember { Animatable(1f) }

    LaunchedEffect(key1 = true) {
        while (true) {
            scale.animateTo(
                targetValue = 1.05f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = 30f
                ),
            )
            scale.animateTo(
                targetValue = 1f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = 30f
                ),
            )
        }
    }

    Image(
        painter = painterResource(id = R.drawable.ic_pokebol),
        contentDescription = null,
        modifier = Modifier
            .height(120.dp)
            .alpha(animatedAlpha.value)
            .graphicsLayer {
                rotationZ = rotation.value
                scaleX = scale.value
                scaleY = scale.value
            }
    )
}