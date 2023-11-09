package com.example.sigacorfilkom

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LayoutNavigasiBawah(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 1080.dp)
            .requiredHeight(height = 257.dp)
    ) {
        LazyRow(
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .offset(x = 0.dp,
                    y = 0.dp)
                .requiredWidth(width = 1080.dp)
                .requiredHeight(height = 171.dp)
                .background(color = Color.White)
                .padding(horizontal = 40.dp)
        ) {
            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 8.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(bottom = 24.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.iconhome),
                            contentDescription = "Frame",
                            colorFilter = ColorFilter.tint(Color(0xffcbd5e1)),
                            modifier = Modifier
                                .requiredSize(size = 56.dp))
                        Text(
                            text = "Home",
                            color = Color(0xffcbd5e1),
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier
                                .wrapContentHeight(align = Alignment.CenterVertically))
                    }
                }
            }
            item {
                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 8.dp))
            }
            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 8.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(bottom = 24.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.iconhistory),
                            contentDescription = "Frame",
                            colorFilter = ColorFilter.tint(Color(0xffff8b13)),
                            modifier = Modifier
                                .requiredSize(size = 56.dp))
                        Text(
                            text = "History",
                            color = Color(0xffff8b13),
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier
                                .wrapContentHeight(align = Alignment.CenterVertically))
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(x = 0.dp,
                    y = 0.dp)
                .requiredSize(size = 154.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .offset(x = 0.dp,
                        y = 0.dp)
                    .requiredSize(size = 158.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color(0xffffecd8)))
            Image(
                painter = painterResource(id = R.drawable.iconplus),
                contentDescription = "Vector",
                modifier = Modifier
                    .fillMaxSize())
        }
    }
}

@Preview(widthDp = 1080, heightDp = 257)
@Composable
private fun NavigasiBawahPreview() {
    LayoutNavigasiBawah(Modifier)
}