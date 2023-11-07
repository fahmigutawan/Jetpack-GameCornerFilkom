package com.example.sigacorfilkom.ui.theme.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.sigacorfilkom.R

@Composable
fun HalamanUtamaMahasiswa(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 1080.dp)
            .requiredHeight(height = 2400.dp)
            .background(color = Color.White)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(56.dp, Alignment.Top),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 44.dp,
                    y = 277.dp
                )
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 992.dp)
                    .requiredHeight(height = 601.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.Start),
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(
                            x = 0.dp,
                            y = 577.dp
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 24.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xffff8b13))
                    )
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 24.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xffffecd8))
                    )
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 24.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xffffecd8))
                    )
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 24.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xffffecd8))
                    )
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 24.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xffffecd8))
                    )
                }
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(
                            x = 0.dp,
                            y = 0.dp
                        )
                        .requiredWidth(width = 992.dp)
                        .requiredHeight(height = 538.dp)
                        .clip(shape = RoundedCornerShape(24.dp))
                        .background(color = Color.White)
                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.frame),
//                        contentDescription = "Frame",
//                        colorFilter = ColorFilter.tint(Color(0xffcbd5e1)),
//                        modifier = Modifier
//                            .align(alignment = Alignment.TopStart)
//                            .offset(x = 347.dp,
//                                y = 120.dp)
//                            .requiredSize(size = 298.dp))
                }
            }
            LazyRow(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .requiredWidth(width = 992.dp)
                    .requiredHeight(height = 243.dp)
                    .clip(shape = RoundedCornerShape(24.dp))
                    .background(color = Color.White)
            ) {
                item {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            10.dp,
                            Alignment.CenterHorizontally
                        ),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 40.dp)
                    ) {
                        Text(
                            text = "Hi, Nama",
                            style = TextStyle(
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .requiredWidth(width = 538.dp)
                                .wrapContentHeight(align = Alignment.CenterVertically)
                        )
                    }
                }
                item {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            10.dp,
                            Alignment.CenterHorizontally
                        ),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxHeight()
                            .requiredWidth(width = 376.dp)
                            .padding(
                                horizontal = 16.dp,
                                vertical = 24.dp
                            )
                    ) {
                        Text(
                            text = "Wednesday\n25 October 2023",
                            color = Color(0xff334155),
                            textAlign = TextAlign.End,
                            style = TextStyle(
                                fontSize = 40.sp
                            ),
                            modifier = Modifier
                                .wrapContentHeight(align = Alignment.CenterVertically)
                        )
                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(45.dp, Alignment.Top)
            ) {
                Text(
                    text = "Menu",
                    style = TextStyle(
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .requiredWidth(width = 992.dp)
                        .requiredHeight(height = 293.dp)
                        .clip(shape = RoundedCornerShape(24.dp))
                        .background(color = Color.White)
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 842.dp)
                            .requiredHeight(height = 160.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(
                                24.dp,
                                Alignment.CenterVertically
                            ),
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .offset(
                                    x = 116.888916015625.dp,
                                    y = (-17).dp
                                )
                                .requiredWidth(width = 608.dp)
                        ) {
                            Text(
                                text = "Buku Panduan",
                                color = Color(0xffff8b13),
                                lineHeight = 2.08.em,
                                style = TextStyle(
                                    fontSize = 48.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(
                                text = "Panduan melakukan reservasi Game Corner Lengkap",
                                color = Color(0xffff8b13),
                                lineHeight = 4.25.em,
                                style = TextStyle(
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Medium
                                ),
                                modifier = Modifier
                                    .requiredWidth(width = 608.dp)
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.iconbook),
                            contentDescription = "Vector",
                            modifier = Modifier
                                .requiredSize(size = 178.dp)
                        )
                    }
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .requiredWidth(width = 992.dp)
                        .requiredHeight(height = 293.dp)
                        .clip(shape = RoundedCornerShape(24.dp))
                        .background(color = Color.White)
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 842.dp)
                            .requiredHeight(height = 178.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(
                                24.dp,
                                Alignment.CenterVertically
                            ),
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .offset(
                                    x = 116.97222900390625.dp,
                                    y = (-4.6399993896484375).dp
                                )
                                .requiredWidth(width = 608.dp)
                        ) {
                            Text(
                                text = "Lihat Jadwal",
                                color = Color(0xffff8b13),
                                lineHeight = 2.08.em,
                                style = TextStyle(
                                    fontSize = 48.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(
                                text = "Panduan melakukan reservasi Game Corner Lengkap",
                                color = Color(0xffff8b13),
                                lineHeight = 4.25.em,
                                style = TextStyle(
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Medium
                                ),
                                modifier = Modifier
                                    .requiredWidth(width = 608.dp)
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.iconjadwal),
                            contentDescription = "Vector",
                            modifier = Modifier
                                .requiredSize(size = 178.dp)
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 0.dp,
                    y = 2143.dp
                )
                .requiredWidth(width = 1080.dp)
                .requiredHeight(height = 257.dp)
        ) {
            LazyRow(
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .offset(
                        x = 0.dp,
                        y = 0.dp
                    )
                    .requiredWidth(width = 1080.dp)
                    .requiredHeight(height = 171.dp)
                    .background(color = Color.White)
                    .padding(horizontal = 40.dp)
            ) {
                item {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            10.dp,
                            Alignment.CenterHorizontally
                        ),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 8.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(
                                8.dp,
                                Alignment.CenterVertically
                            ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(bottom = 24.dp)
                        ) {
//                            Image(
//                                painter = painterResource(id = R.drawable.frame),
//                                contentDescription = "Frame",
//                                colorFilter = ColorFilter.tint(Color(0xffff8b13)),
//                                modifier = Modifier
//                                    .requiredSize(size = 56.dp))
                            Text(
                                text = "Home",
                                color = Color(0xffff8b13),
                                style = MaterialTheme.typography.headlineLarge,
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically)
                            )
                        }
                    }
                }
                item {
                    Spacer(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 8.dp)
                    )
                }
                item {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            10.dp,
                            Alignment.CenterHorizontally
                        ),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 8.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(
                                8.dp,
                                Alignment.CenterVertically
                            ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(bottom = 24.dp)
                        ) {
//                            Image(
//                                painter = painterResource(id = R.drawable.frame),
//                                contentDescription = "Frame",
//                                colorFilter = ColorFilter.tint(Color(0xffcbd5e1)),
//                                modifier = Modifier
//                                    .requiredSize(size = 56.dp))
                            Text(
                                text = "History",
                                color = Color(0xffcbd5e1),
                                style = MaterialTheme.typography.headlineLarge,
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically)
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = 0.dp,
                        y = 0.dp
                    )
                    .requiredSize(size = 154.dp)
            ) {
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .offset(
                            x = 0.dp,
                            y = 0.dp
                        )
                        .requiredSize(size = 158.dp)
                        .clip(shape = CircleShape)
                        .background(color = Color(0xffffecd8))
                )
//                Image(
//                    painter = painterResource(id = R.drawable.vector),
//                    contentDescription = "Vector",
//                    modifier = Modifier
//                        .fillMaxSize())
            }
        }
        Column() {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 1080.dp)
                    .requiredHeight(height = 80.dp)
                    .background(color = Color(0xff0f172a))
            ) {
                Text(
                    text = "Notification",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .offset(
                            x = (-270.5).dp,
                            y = (-0.5).dp
                        )
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
            }
            Box(
                modifier = Modifier
                    .requiredWidth(width = 1080.dp)
                    .requiredHeight(height = 144.dp)
                    .background(color = Color.White)
            ) {
                Text(
                    text = "SiGACOR",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .offset(
                            x = 0.dp,
                            y = 0.dp
                        )
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
//                Image(
//                    painter = painterResource(id = R.drawable.frame),
//                    contentDescription = "Frame",
//                    colorFilter = ColorFilter.tint(Color.Black),
//                    modifier = Modifier
//                        .align(alignment = Alignment.TopStart)
//                        .offset(x = 998.dp,
//                            y = 47.dp)
//                        .requiredSize(size = 49.dp))
            }
        }
    }
}

@Preview(widthDp = 1080, heightDp = 2400)
@Composable
private fun HalamanUtamaMahasiswaPreview() {
    HalamanUtamaMahasiswa(Modifier)
}