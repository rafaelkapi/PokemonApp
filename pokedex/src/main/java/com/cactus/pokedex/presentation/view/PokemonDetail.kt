package com.cactus.pokedex.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.cactus.movie.R
import com.cactus.pokedex.presentation.model.PokemonType
import com.cactus.pokedex.presentation.model.PokemonVo
import com.cactus.pokedex.ui.theme.ColorAccent
import com.cactus.pokedex.ui.theme.PrimaryColor

@Composable
fun PokemonDetail(pokemon: PokemonVo) {
    val offsetYGeometricBackground = -80.dp
    val offsetXGeometricBackground = -30.dp
    val compensationShadowHeight = 30.dp


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryColor)
    ) {
        PokemonImage()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = -compensationShadowHeight)
        ) {
            Image(
                painter = painterResource(id = R.drawable.geometric_background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .offset(offsetXGeometricBackground, offsetYGeometricBackground)
                    .alpha(0.1f)
            )
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                PokemonEvolution()
            }
        }
    }
}

@Composable
fun PokemonEvolution() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 25.dp,
                        topEnd = 25.dp,
                        bottomEnd = 25.dp,
                        bottomStart = 25.dp,
                    )
                )
                .alpha(.8f)
                .background(Color.Black)
                .zIndex(1f)

        ) {
            Text(
                modifier = Modifier.padding(start = 10.dp, top = 5.dp, bottom =5.dp, end =10.dp),
                text = "Evolutions", color = Color.White, style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    lineHeight = 28.sp,
                    letterSpacing = 0.sp
                )
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.18f)
                .offset(y = -15.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomEnd = 30.dp,
                        bottomStart = 30.dp,
                    )
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(.5f)
                    .background(Color.Black)
            )

            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                val fillMaxHeightThumbnailsEvolution = 0.7f
                val borderWidth = 3.dp
                val borderColor = Color(0xA9FFFFFF)
                val arrowFillMaxHeight = 0.3f

                AsyncImage(
                    model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/172.png",
                    contentDescription = null,
                    modifier = Modifier
                        .alpha(.9f)
                        .fillMaxHeight(fillMaxHeightThumbnailsEvolution)
                        .padding(borderWidth)
                )
                Image(
                    painter = painterResource(id = R.drawable.arrow_evolution),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight(arrowFillMaxHeight),
                    colorFilter = ColorFilter.tint(borderColor)
                )
                AsyncImage(
                    model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
                    contentDescription = null,
                    modifier = Modifier
                        .alpha(.9f)
                        .fillMaxHeight(fillMaxHeightThumbnailsEvolution)
                        .padding(borderWidth)
                )
                Image(
                    painter = painterResource(id = R.drawable.arrow_evolution),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight(arrowFillMaxHeight),
                    colorFilter = ColorFilter.tint(borderColor),
                )
                AsyncImage(
                    model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/26.png",
                    contentDescription = null,
                    modifier = Modifier
                        .alpha(.9f)
                        .fillMaxHeight(fillMaxHeightThumbnailsEvolution)
                        .padding(borderWidth)
                )
            }
        }
    }
}


@Composable
fun PokemonImage() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {

        BackgroundImage()

        AsyncImage(
            model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/25.png",
            contentDescription = null,
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(14f / 9f)
                .alpha(0.9f),
        )
    }
}

@Composable
fun BackgroundImage() {
    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(ColorAccent.value),
            Color.Transparent
        ),
        startY = 80f,
        endY = 0f,
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        val imageShadowHeight = 40.dp

        Image(
            painter = painterResource(id = R.drawable.backgroud_detail_1),
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
}

@Preview(showBackground = true)
@Composable
fun PreviewPokemonDetail() {

    val pokemon = PokemonVo(
        "#0025",
        "Pikachu",
        listOf(
            PokemonType(
                "Electric", R.drawable.ic_grass, 324
            ),
        ),
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
    )
    PokemonDetail(pokemon)
}