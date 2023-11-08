package com.example.sigacorfilkom

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LayoutRiwayatReservasi(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 1080.dp)
            .requiredHeight(height = 2400.dp)
            .background(color = Color.White)
    ) {
        LazyColumn(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp,
                    y = 328.dp)
                .requiredHeight(height = 1901.dp)
        ) {
            item {
                Surface(
                    color = Color.White,
                    border = BorderStroke(2.dp, Color(0xffcbd5e1))
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 1080.dp)
                            .requiredHeight(height = 200.dp)
                    ) {
                        Text(
                            text = "Waiting",
                            color = Color(0xffffbc05),
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .offset(x = 401.dp,
                                    y = (-0.5).dp)
                                .wrapContentHeight(align = Alignment.CenterVertically))
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.CenterStart)
                                .offset(x = 0.dp,
                                    y = 0.dp)
                                .requiredSize(size = 200.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.Center)
                                    .offset(x = 0.dp,
                                        y = 0.dp)
                                    .requiredSize(size = 130.dp)
                                    .clip(shape = CircleShape)
                                    .background(color = Color(0xffd9d9d9)))
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 237.dp,
                                    y = 49.dp)
                        ) {
                            Text(
                                text = "Nama Device",
                                color = Color.Black,
                                style = TextStyle(
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                            Text(
                                text = "25 Oct 2023 sesi 1",
                                color = Color(0xff3d3d3d),
                                style = TextStyle(
                                    fontSize = 40.sp),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                        }
                    }
                }
            }
            item {
                Surface(
                    color = Color.White,
                    border = BorderStroke(2.dp, Color(0xffcbd5e1))
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 1080.dp)
                            .requiredHeight(height = 200.dp)
                    ) {
                        Text(
                            text = "Sukses",
                            color = Color(0xff0cd373),
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .offset(x = 402.5.dp,
                                    y = (-0.5).dp)
                                .wrapContentHeight(align = Alignment.CenterVertically))
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.CenterStart)
                                .offset(x = 0.dp,
                                    y = 0.dp)
                                .requiredSize(size = 200.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.Center)
                                    .offset(x = 0.dp,
                                        y = 0.dp)
                                    .requiredSize(size = 130.dp)
                                    .clip(shape = CircleShape)
                                    .background(color = Color(0xffd9d9d9)))
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 237.dp,
                                    y = 49.dp)
                        ) {
                            Text(
                                text = "Nama Device",
                                color = Color.Black,
                                style = TextStyle(
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                            Text(
                                text = "25 Oct 2023 sesi 1",
                                color = Color(0xff3d3d3d),
                                style = TextStyle(
                                    fontSize = 40.sp),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                        }
                    }
                }
            }
            item {
                Surface(
                    color = Color.White,
                    border = BorderStroke(2.dp, Color(0xffcbd5e1))
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 1080.dp)
                            .requiredHeight(height = 200.dp)
                    ) {
                        Text(
                            text = "Gagal",
                            color = Color(0xffe84c3d),
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .offset(x = 413.5.dp,
                                    y = (-0.5).dp)
                                .wrapContentHeight(align = Alignment.CenterVertically))
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.CenterStart)
                                .offset(x = 0.dp,
                                    y = 0.dp)
                                .requiredSize(size = 200.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.Center)
                                    .offset(x = 0.dp,
                                        y = 0.dp)
                                    .requiredSize(size = 130.dp)
                                    .clip(shape = CircleShape)
                                    .background(color = Color(0xffd9d9d9)))
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 237.dp,
                                    y = 49.dp)
                        ) {
                            Text(
                                text = "Nama Device",
                                color = Color.Black,
                                style = TextStyle(
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                            Text(
                                text = "25 Oct 2023 sesi 1",
                                color = Color(0xff3d3d3d),
                                style = TextStyle(
                                    fontSize = 40.sp),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                        }
                    }
                }
            }
            item {
                Surface(
                    color = Color.White,
                    border = BorderStroke(2.dp, Color(0xffcbd5e1))
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 1080.dp)
                            .requiredHeight(height = 200.dp)
                    ) {
                        Text(
                            text = "Sukses",
                            color = Color(0xff0cd373),
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .offset(x = 402.5.dp,
                                    y = (-0.5).dp)
                                .wrapContentHeight(align = Alignment.CenterVertically))
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.CenterStart)
                                .offset(x = 0.dp,
                                    y = 0.dp)
                                .requiredSize(size = 200.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.Center)
                                    .offset(x = 0.dp,
                                        y = 0.dp)
                                    .requiredSize(size = 130.dp)
                                    .clip(shape = CircleShape)
                                    .background(color = Color(0xffd9d9d9)))
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 237.dp,
                                    y = 49.dp)
                        ) {
                            Text(
                                text = "Nama Device",
                                color = Color.Black,
                                style = TextStyle(
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                            Text(
                                text = "25 Oct 2023 sesi 1",
                                color = Color(0xff3d3d3d),
                                style = TextStyle(
                                    fontSize = 40.sp),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                        }
                    }
                }
            }
            item {
                Surface(
                    color = Color.White,
                    border = BorderStroke(2.dp, Color(0xffcbd5e1))
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 1080.dp)
                            .requiredHeight(height = 200.dp)
                    ) {
                        Text(
                            text = "Gagal",
                            color = Color(0xffe84c3d),
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .offset(x = 413.5.dp,
                                    y = (-0.5).dp)
                                .wrapContentHeight(align = Alignment.CenterVertically))
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.CenterStart)
                                .offset(x = 0.dp,
                                    y = 0.dp)
                                .requiredSize(size = 200.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.Center)
                                    .offset(x = 0.dp,
                                        y = 0.dp)
                                    .requiredSize(size = 130.dp)
                                    .clip(shape = CircleShape)
                                    .background(color = Color(0xffd9d9d9)))
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 237.dp,
                                    y = 49.dp)
                        ) {
                            Text(
                                text = "Nama Device",
                                color = Color.Black,
                                style = TextStyle(
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                            Text(
                                text = "25 Oct 2023 sesi 1",
                                color = Color(0xff3d3d3d),
                                style = TextStyle(
                                    fontSize = 40.sp),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                        }
                    }
                }
            }
            item {
                Surface(
                    color = Color.White,
                    border = BorderStroke(2.dp, Color(0xffcbd5e1))
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 1080.dp)
                            .requiredHeight(height = 200.dp)
                    ) {
                        Text(
                            text = "Sukses",
                            color = Color(0xff0cd373),
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .offset(x = 402.5.dp,
                                    y = (-0.5).dp)
                                .wrapContentHeight(align = Alignment.CenterVertically))
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.CenterStart)
                                .offset(x = 0.dp,
                                    y = 0.dp)
                                .requiredSize(size = 200.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.Center)
                                    .offset(x = 0.dp,
                                        y = 0.dp)
                                    .requiredSize(size = 130.dp)
                                    .clip(shape = CircleShape)
                                    .background(color = Color(0xffd9d9d9)))
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 237.dp,
                                    y = 49.dp)
                        ) {
                            Text(
                                text = "Nama Device",
                                color = Color.Black,
                                style = TextStyle(
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                            Text(
                                text = "25 Oct 2023 sesi 1",
                                color = Color(0xff3d3d3d),
                                style = TextStyle(
                                    fontSize = 40.sp),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                        }
                    }
                }
            }
            item {
                Surface(
                    color = Color.White,
                    border = BorderStroke(2.dp, Color(0xffcbd5e1))
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 1080.dp)
                            .requiredHeight(height = 200.dp)
                    ) {
                        Text(
                            text = "Gagal",
                            color = Color(0xffe84c3d),
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .offset(x = 413.5.dp,
                                    y = (-0.5).dp)
                                .wrapContentHeight(align = Alignment.CenterVertically))
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.CenterStart)
                                .offset(x = 0.dp,
                                    y = 0.dp)
                                .requiredSize(size = 200.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.Center)
                                    .offset(x = 0.dp,
                                        y = 0.dp)
                                    .requiredSize(size = 130.dp)
                                    .clip(shape = CircleShape)
                                    .background(color = Color(0xffd9d9d9)))
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 237.dp,
                                    y = 49.dp)
                        ) {
                            Text(
                                text = "Nama Device",
                                color = Color.Black,
                                style = TextStyle(
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                            Text(
                                text = "25 Oct 2023 sesi 1",
                                color = Color(0xff3d3d3d),
                                style = TextStyle(
                                    fontSize = 40.sp),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                        }
                    }
                }
            }
            item {
                Surface(
                    color = Color.White,
                    border = BorderStroke(2.dp, Color(0xffcbd5e1))
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 1080.dp)
                            .requiredHeight(height = 200.dp)
                    ) {
                        Text(
                            text = "Sukses",
                            color = Color(0xff0cd373),
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .offset(x = 402.5.dp,
                                    y = (-0.5).dp)
                                .wrapContentHeight(align = Alignment.CenterVertically))
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.CenterStart)
                                .offset(x = 0.dp,
                                    y = 0.dp)
                                .requiredSize(size = 200.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.Center)
                                    .offset(x = 0.dp,
                                        y = 0.dp)
                                    .requiredSize(size = 130.dp)
                                    .clip(shape = CircleShape)
                                    .background(color = Color(0xffd9d9d9)))
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 237.dp,
                                    y = 49.dp)
                        ) {
                            Text(
                                text = "Nama Device",
                                color = Color.Black,
                                style = TextStyle(
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                            Text(
                                text = "25 Oct 2023 sesi 1",
                                color = Color(0xff3d3d3d),
                                style = TextStyle(
                                    fontSize = 40.sp),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                        }
                    }
                }
            }
            item {
                Surface(
                    color = Color.White,
                    border = BorderStroke(2.dp, Color(0xffcbd5e1))
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 1080.dp)
                            .requiredHeight(height = 200.dp)
                    ) {
                        Text(
                            text = "Gagal",
                            color = Color(0xffe84c3d),
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .offset(x = 413.5.dp,
                                    y = (-0.5).dp)
                                .wrapContentHeight(align = Alignment.CenterVertically))
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.CenterStart)
                                .offset(x = 0.dp,
                                    y = 0.dp)
                                .requiredSize(size = 200.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.Center)
                                    .offset(x = 0.dp,
                                        y = 0.dp)
                                    .requiredSize(size = 130.dp)
                                    .clip(shape = CircleShape)
                                    .background(color = Color(0xffd9d9d9)))
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 237.dp,
                                    y = 49.dp)
                        ) {
                            Text(
                                text = "Nama Device",
                                color = Color.Black,
                                style = TextStyle(
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                            Text(
                                text = "25 Oct 2023 sesi 1",
                                color = Color(0xff3d3d3d),
                                style = TextStyle(
                                    fontSize = 40.sp),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                        }
                    }
                }
            }
            item {
                Surface(
                    color = Color.White,
                    border = BorderStroke(2.dp, Color(0xffcbd5e1))
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 1080.dp)
                            .requiredHeight(height = 200.dp)
                    ) {
                        Text(
                            text = "Sukses",
                            color = Color(0xff0cd373),
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .offset(x = 402.5.dp,
                                    y = (-0.5).dp)
                                .wrapContentHeight(align = Alignment.CenterVertically))
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.CenterStart)
                                .offset(x = 0.dp,
                                    y = 0.dp)
                                .requiredSize(size = 200.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.Center)
                                    .offset(x = 0.dp,
                                        y = 0.dp)
                                    .requiredSize(size = 130.dp)
                                    .clip(shape = CircleShape)
                                    .background(color = Color(0xffd9d9d9)))
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 237.dp,
                                    y = 49.dp)
                        ) {
                            Text(
                                text = "Nama Device",
                                color = Color.Black,
                                style = TextStyle(
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                            Text(
                                text = "25 Oct 2023 sesi 1",
                                color = Color(0xff3d3d3d),
                                style = TextStyle(
                                    fontSize = 40.sp),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                        }
                    }
                }
            }
            item {
                Surface(
                    color = Color.White,
                    border = BorderStroke(2.dp, Color(0xffcbd5e1))
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 1080.dp)
                            .requiredHeight(height = 200.dp)
                    ) {
                        Text(
                            text = "Gagal",
                            color = Color(0xffe84c3d),
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .offset(x = 413.5.dp,
                                    y = (-0.5).dp)
                                .wrapContentHeight(align = Alignment.CenterVertically))
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.CenterStart)
                                .offset(x = 0.dp,
                                    y = 0.dp)
                                .requiredSize(size = 200.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.Center)
                                    .offset(x = 0.dp,
                                        y = 0.dp)
                                    .requiredSize(size = 130.dp)
                                    .clip(shape = CircleShape)
                                    .background(color = Color(0xffd9d9d9)))
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 237.dp,
                                    y = 49.dp)
                        ) {
                            Text(
                                text = "Nama Device",
                                color = Color.Black,
                                style = TextStyle(
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                            Text(
                                text = "25 Oct 2023 sesi 1",
                                color = Color(0xff3d3d3d),
                                style = TextStyle(
                                    fontSize = 40.sp),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                        }
                    }
                }
            }
            item {
                Surface(
                    color = Color.White,
                    border = BorderStroke(2.dp, Color(0xffcbd5e1))
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 1080.dp)
                            .requiredHeight(height = 200.dp)
                    ) {
                        Text(
                            text = "Sukses",
                            color = Color(0xff0cd373),
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .offset(x = 402.5.dp,
                                    y = (-0.5).dp)
                                .wrapContentHeight(align = Alignment.CenterVertically))
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.CenterStart)
                                .offset(x = 0.dp,
                                    y = 0.dp)
                                .requiredSize(size = 200.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.Center)
                                    .offset(x = 0.dp,
                                        y = 0.dp)
                                    .requiredSize(size = 130.dp)
                                    .clip(shape = CircleShape)
                                    .background(color = Color(0xffd9d9d9)))
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 237.dp,
                                    y = 49.dp)
                        ) {
                            Text(
                                text = "Nama Device",
                                color = Color.Black,
                                style = TextStyle(
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                            Text(
                                text = "25 Oct 2023 sesi 1",
                                color = Color(0xff3d3d3d),
                                style = TextStyle(
                                    fontSize = 40.sp),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically))
                        }
                    }
                }
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
                        .offset(x = (-270.5).dp,
                            y = (-0.5).dp)
                        .wrapContentHeight(align = Alignment.CenterVertically))
            }
            Box(
                modifier = Modifier
                    .requiredWidth(width = 1080.dp)
                    .requiredHeight(height = 144.dp)
                    .background(color = Color.White)
            ) {
                Text(
                    text = "History",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .offset(x = 0.5.dp,
                            y = 0.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically))
            }
            LazyRow(
                modifier = Modifier
                    .requiredWidth(width = 1080.dp)
                    .requiredHeight(height = 104.dp)
                    .background(color = Color.White)
                    .padding(horizontal = 200.dp)
            ) {
                item {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 8.dp)
                    ) {
                        Text(
                            text = "Day",
                            color = Color(0xffcbd5e1),
                            style = TextStyle(
                                fontSize = 40.sp),
                            modifier = Modifier
                                .wrapContentHeight(align = Alignment.CenterVertically))
                    }
                }
                item {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 8.dp)
                    ) {
                        Text(
                            text = "Week",
                            color = Color(0xffcbd5e1),
                            style = TextStyle(
                                fontSize = 40.sp),
                            modifier = Modifier
                                .wrapContentHeight(align = Alignment.CenterVertically))
                    }
                }
                item {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 8.dp)
                    ) {
                        Text(
                            text = "Month",
                            color = Color(0xffff8b13),
                            style = TextStyle(
                                fontSize = 40.sp),
                            modifier = Modifier
                                .wrapContentHeight(align = Alignment.CenterVertically))
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp,
                    y = 2143.dp)
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
//                            Image(
//                                painter = painterResource(id = R.drawable.frame),
//                                contentDescription = "Frame",
//                                colorFilter = ColorFilter.tint(Color(0xffcbd5e1)),
//                                modifier = Modifier
//                                    .requiredSize(size = 56.dp))
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
//                            Image(
//                                painter = painterResource(id = R.drawable.frame),
//                                contentDescription = "Frame",
//                                colorFilter = ColorFilter.tint(Color(0xffff8b13)),
//                                modifier = Modifier
//                                    .requiredSize(size = 56.dp))
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
//                Image(
//                    painter = painterResource(id = R.drawable.vector),
//                    contentDescription = "Vector",
//                    modifier = Modifier
//                        .fillMaxSize())
            }
        }
    }
}

@Preview(widthDp = 1080, heightDp = 2400)
@Composable
private fun HalamanRiwayatReservasiPreview() {
    LayoutRiwayatReservasi(Modifier)
}