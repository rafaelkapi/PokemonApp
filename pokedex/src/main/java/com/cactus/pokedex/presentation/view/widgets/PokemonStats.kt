package com.cactus.pokedex.presentation.view.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import com.cactus.pokedex.presentation.model.PokemonStatsVo
import com.cactus.pokedex.ui.theme.ColorAccent
import com.cactus.pokedex.ui.theme.PrimaryColor

@Composable
fun PokemonStats(modifier: Modifier,stats: PokemonStatsVo) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (background, chart) = createRefs()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(background) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .aspectRatio(4.5f / 3f)
                .clip(
                    RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomEnd = 10.dp,
                        bottomStart = 10.dp,
                    )
                )
                .alpha(.4f)
                .background(Color(ColorAccent.value))
        )

        Row(modifier = Modifier
            .constrainAs(chart) {
                top.linkTo(background.top)
                start.linkTo(background.start)
                end.linkTo(background.end)
                bottom.linkTo(background.bottom)
            }
            .background(Color.Transparent)
            .padding(start = 15.dp, end = 10.dp)
        ) {
            BarChart(
                modifier = Modifier.weight(1f),
                range = stats.hp,
                specialRange = null
            )
            BarChart(
                modifier = Modifier.weight(1f),
                range = stats.attack,
                specialRange = stats.specialAttack
            )
            BarChart(
                modifier = Modifier.weight(1f),
                range = stats.defense,
                specialRange = stats.specialDefense

            )
            BarChart(
                modifier = Modifier.weight(1f),
                range = stats.speed,
                specialRange = null

            )
        }
    }
}

@Composable
fun BarChart(modifier: Modifier, range: Pair<String, Float>, specialRange: Float?) {
    val valueRange = 15 * range.second
    val valueSpecialRange = 15 * (specialRange ?: 0f)
    Column(modifier = modifier) {
        LazyColumn(
            reverseLayout = true
        ) {
            items(15) { index ->
                Box(
                    modifier = Modifier
                        .aspectRatio(7.5f / 1f)
                        .padding(start = 5.dp, end = 5.dp, top = 2.dp, bottom = 2.dp)
                        .background(
                            if (index <= valueRange)
                                Color(0xFF2AB9CF)
                            else if (index <= valueSpecialRange)
                                Color(0xFFBAE5EC)
                            else
                                Color(PrimaryColor.value)
                        )
                )
            }
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
            ,
            text = range.first, color = Color(0xFF2D2D2E), style = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                lineHeight = 12.sp,
                letterSpacing = 0.sp
            )
        )

        specialRange?.let {
            val text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = Color(0xFF7EDAE9),
                    fontSize = 18.sp,


                )) {
                    append("◉ ")
                }
                append("Special")
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally),
                text = text, color = Color(0xFF5B5B5C), style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    letterSpacing = 0.sp
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonStatsPreview() {
    val stats = PokemonStatsVo(
        hp = "HP" to 0.3f,
        attack = "Attack" to 0.55f,
        defense = "Defense" to 0.7f,
        specialAttack = 0.7f,
        specialDefense = 0.8f,
        speed = "Speed" to 0.5f
    )
    PokemonStats(Modifier.fillMaxWidth(),stats)
}