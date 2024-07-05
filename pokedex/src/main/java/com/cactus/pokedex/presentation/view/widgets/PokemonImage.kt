package com.cactus.pokedex.presentation.view.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.cactus.movie.R
import com.cactus.pokedex.ui.theme.ColorAccent
import com.cactus.pokedex.ui.theme.PrimaryColor

@Composable
fun PokemonImage(modifier: Modifier, name: String, imageUrl: String, drawableBackground : Int) {
    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(ColorAccent.value),
            Color.Transparent
        ),
        startY = 70f,
        endY = 0f,
    )

    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            val imageShadowHeight = 40.dp

            Image(
                painter = painterResource(id = drawableBackground),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(14f / 9f)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageShadowHeight)
                    .offset(y = -imageShadowHeight * 5 / 6)
                    .clip(
                        RoundedCornerShape(
                            bottomEnd = 10.dp, bottomStart = 10.dp
                        )
                    )
                    .background(gradient)
            )
        }

        Text(
            text = name,
            color = Color(PrimaryColor.value),
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.sp
            ),
            modifier = Modifier
                .align(Alignment.BottomStart)
            .offset(y= -45.dp, x = 10.dp)
        )

        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(14f / 9f)
                .alpha(0.9f),
        )


    }
}