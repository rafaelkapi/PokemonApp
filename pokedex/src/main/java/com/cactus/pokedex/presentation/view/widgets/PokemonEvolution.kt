package com.cactus.pokedex.presentation.view.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.cactus.movie.R
import com.cactus.pokedex.presentation.model.EvolutionVo
import com.cactus.pokedex.presentation.model.PokemonDetailVo

@Composable
fun PokemonEvolution(modifier: Modifier, detailVo: PokemonDetailVo) {
    ConstraintLayout(modifier = modifier) {
        val (title, background, spacer) = createRefs()

        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .aspectRatio(6f / 2f)
                .constrainAs(background) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .clip(
                    RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomEnd = 30.dp,
                        bottomStart = 30.dp,
                    )
                )
                .alpha(.5f)
                .background(Color.Black)
        )

        Box(
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(background.top)
                    bottom.linkTo(background.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
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
                modifier = Modifier.padding(start = 10.dp, top = 5.dp, bottom = 5.dp, end = 10.dp),
                text = "Evolutions", color = Color.White, style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    lineHeight = 28.sp,
                    letterSpacing = 0.sp
                )
            )
        }


        Row(
            modifier = Modifier
                .constrainAs(spacer) {
                    top.linkTo(background.top)
                    bottom.linkTo(background.bottom)
                    start.linkTo(background.start)
                    end.linkTo(background.end)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            val borderColor = Color(0xA9FFFFFF)
            val arrowFillMaxHeight = 0.2f

            PosterThumbnail(detailVo.evolutionsThumbnails.get(0))

            Image(
                painter = painterResource(id = R.drawable.arrow_evolution),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight(arrowFillMaxHeight),
                colorFilter = ColorFilter.tint(borderColor)
            )

            PosterThumbnail(detailVo.evolutionsThumbnails.get(1))

            Image(
                painter = painterResource(id = R.drawable.arrow_evolution),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight(arrowFillMaxHeight),
                colorFilter = ColorFilter.tint(borderColor),
            )

            PosterThumbnail(detailVo.evolutionsThumbnails.get(2))
        }
    }
}

@Composable
fun PosterThumbnail(vo: EvolutionVo) {
    val borderWidth = 3.dp
    val fillMaxHeightThumbnailsEvolution = 0.6f

    with(vo) {
        Column {
            AsyncImage(
                model = posterUrl,
                contentDescription = null,
                modifier = Modifier
                    .alpha(.9f)
                    .fillMaxHeight(fillMaxHeightThumbnailsEvolution)
                    .padding(borderWidth)
            )
            Text(
                modifier = Modifier
                    .padding(
                        start = 10.dp
                    )
                    .alpha(.7f),
                text = "#$id", color = Color.White, style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    lineHeight = 14.sp,
                    letterSpacing = 0.sp
                )
            )
        }
    }
}