package com.cactus.pokedex.presentation.view.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cactus.pokedex.presentation.model.PokemonSpecVo
import com.cactus.pokedex.ui.theme.ColorAccent
import com.cactus.pokedex.ui.theme.PrimaryColor

@Composable
fun PokemonSpec(modifier: Modifier, spec: PokemonSpecVo) {
    Column(modifier = modifier, verticalArrangement = Arrangement.SpaceAround) {
        HeaderSpec(spec)

        TagList(
            Modifier
                .padding(start = 8.dp), title = "Type", tags = spec.type
        )

        TagList(
            Modifier
                .padding(start = 8.dp), title = "Weaknesses", tags = spec.weaknesses
        )
    }
}

@Composable
fun HeaderSpec(spec: PokemonSpecVo) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp,
                        bottomEnd = 20.dp,
                        bottomStart = 20.dp,
                    )
                )
                .background(Color(ColorAccent.value))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                Text(
                    text = "Height", color = Color(PrimaryColor.value), style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        lineHeight = 15.sp,
                        letterSpacing = 0.sp
                    )
                )

                Text(
                    text = "Weight", color = Color(PrimaryColor.value), style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        lineHeight = 15.sp,
                        letterSpacing = 0.sp
                    )
                )

                Text(
                    text = "Category", color = Color(PrimaryColor.value), style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        lineHeight = 15.sp,
                        letterSpacing = 0.sp
                    )
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Text(
                text = spec.height, color = Color(0xFF49494B), style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    lineHeight = 15.sp,
                    letterSpacing = 0.sp
                )
            )

            Text(
                text = spec.weight, color = Color(0xFF49494B), style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    lineHeight = 15.sp,
                    letterSpacing = 0.sp
                )
            )

            Text(
                text = spec.category, color = Color(0xFF49494B), style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    lineHeight = 15.sp,
                    letterSpacing = 0.sp
                )
            )
        }

    }
}

@Composable
fun TagList(modifier: Modifier = Modifier, title: String, tags: List<Pair<Int, String>>) {
    Column(modifier = modifier) {
        Text(
            text = title, color = Color(0xFF424242), style = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                letterSpacing = 0.sp
            )
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.padding(8.dp)
        ) {
            items(tags.size) { index ->
                val tag = tags[index]
                TypeTag(
                    Modifier.padding(2.dp),
                    tag.second,
                    tag.first
                )
            }
        }
    }
}

@Composable
fun TypeTag(modifier: Modifier = Modifier, type: String, icon: Int) {
    Box(
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp,
                    bottomEnd = 20.dp,
                    bottomStart = 20.dp,
                )
            )
            .alpha(.8f)
            .background(Color(ColorAccent.value))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(14.dp)
                    .padding(start = 5.dp)
                    .aspectRatio(1f / 1f)
            )

            Text(
                modifier = Modifier.padding(
                    start = 4.dp,
                    top = 4.dp,
                    bottom = 4.dp,
                    end = 5.dp
                ),
                text = type, color = Color.White, style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    letterSpacing = 0.sp
                )
            )
        }
    }
}