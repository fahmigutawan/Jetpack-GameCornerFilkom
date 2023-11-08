package com.example.sigacorfilkom

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
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

data class JadwalPageItem(
    val textPilihTanggal: String,
    val textNovemberWeek: String,
    val textText: String,
    val textMo: String,
    val textTu: String,
    val textWe: String,
    val textTh: String,
    val textFr: String,
    val textSa: String,
    val textSu: String,
    val gridData55_812: String,
    val gridData55_814: String,
    val gridData55_816
    : String,
    val gridData55_818
    : String,
    val gridData55_820
    : String,
    val gridData55_822
    : String,
    val gridData55_824
    : String,
    val gridData55_937
    : String,
    val gridData55_939
    : String,
    val gridData55_941
    : String,
    val gridData55_943
    : String,
    val gridData55_945
    : String,
    val gridData55_947
    : String,
    val gridData55_949
    : String
)

@Composable
fun LayoutJadwal(modifier: Modifier = Modifier) {
    val gridData = listOf(
        JadwalPageItem(
            "Pilih Tanggal",
            "November 2023 - Week 1",
            " ",
            "Mo",
            "Tu",
            "We",
            "Th",
            "Fr",
            "Sa",
            "Su",
            "30",
            "31",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12"
        ),
        JadwalPageItem(
            "Pilih Tanggal",
            "November 2023 - Week 1",
            " ",
            "Mo",
            "Tu",
            "We",
            "Th",
            "Fr",
            "Sa",
            "Su",
            "30",
            "31",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12"
        ),
        JadwalPageItem(
            "Pilih Tanggal",
            "November 2023 - Week 1",
            " ",
            "Mo",
            "Tu",
            "We",
            "Th",
            "Fr",
            "Sa",
            "Su",
            "30",
            "31",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12"
        ),
        JadwalPageItem(
            "Pilih Tanggal",
            "November 2023 - Week 1",
            " ",
            "Mo",
            "Tu",
            "We",
            "Th",
            "Fr",
            "Sa",
            "Su",
            "30",
            "31",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12"
        ),
        JadwalPageItem(
            "Pilih Tanggal",
            "November 2023 - Week 1",
            " ",
            "Mo",
            "Tu",
            "We",
            "Th",
            "Fr",
            "Sa",
            "Su",
            "30",
            "31",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12"
        ),
        JadwalPageItem(
            "Pilih Tanggal",
            "November 2023 - Week 1",
            " ",
            "Mo",
            "Tu",
            "We",
            "Th",
            "Fr",
            "Sa",
            "Su",
            "30",
            "31",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12"
        ),
        JadwalPageItem(
            "Pilih Tanggal",
            "November 2023 - Week 1",
            " ",
            "Mo",
            "Tu",
            "We",
            "Th",
            "Fr",
            "Sa",
            "Su",
            "30",
            "31",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12"
        ),
        JadwalPageItem(
            "Pilih Tanggal",
            "November 2023 - Week 1",
            " ",
            "Mo",
            "Tu",
            "We",
            "Th",
            "Fr",
            "Sa",
            "Su",
            "30",
            "31",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12"
        )
    )

    LazyHorizontalGrid(
        rows = GridCells.Fixed(4),
        verticalArrangement = Arrangement.spacedBy(72.dp),
        content = {
            items(gridData.size) { index ->
                val textPilihTanggal = gridData[index].textPilihTanggal
                val textNovemberWeek = gridData[index].textNovemberWeek
                val textText = gridData[index].textText
                val textMo = gridData[index].textMo
                val textTu = gridData[index].textTu
                val textWe = gridData[index].textWe
                val textTh = gridData[index].textTh
                val textFr = gridData[index].textFr
                val textSa = gridData[index].textSa
                val textSu = gridData[index].textSu
                val gridData55_812 = gridData[index].gridData55_812
                val gridData55_814 = gridData[index].gridData55_814
                val gridData55_816 = gridData[index].gridData55_816
                val gridData55_818 = gridData[index].gridData55_818
                val gridData55_820 = gridData[index].gridData55_820
                val gridData55_822 = gridData[index].gridData55_822
                val gridData55_824 = gridData[index].gridData55_824
                val gridData55_937 = gridData[index].gridData55_937
                val gridData55_939 = gridData[index].gridData55_939
                val gridData55_941 = gridData[index].gridData55_941
                val gridData55_943 = gridData[index].gridData55_943
                val gridData55_945 = gridData[index].gridData55_945
                val gridData55_947 = gridData[index].gridData55_947
                val gridData55_949 = gridData[index].gridData55_949
                Column(
                    verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.Top)
                ) {
                    Text(
                        text = textPilihTanggal,
                        style = TextStyle(
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                    Column(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(24.dp))
                            .background(color = Color.White)
                            .padding(all = 48.dp)
                    ) {
                        LazyRow(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            item {
                                Text(
                                    text = textNovemberWeek,
                                    color = Color(0xff1e293b),
                                    style = TextStyle(
                                        fontSize = 48.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = Modifier
                                        .wrapContentHeight(align = Alignment.CenterVertically)
                                )
                            }
                            item {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 32.dp)
                                ) {
                                    Text(
                                        text = textText,
                                        color = Color(0xff1e293b),
                                        style = TextStyle(
                                            fontSize = 48.sp,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                    )
                                }
                            }
                            item {
                                LazyVerticalGrid(
                                    columns = GridCells.Fixed(1),
                                    content = {
                                        items(gridData.size) { index ->
//                                            Image(
//                                                painter = painterResource(id = imageForward),
//                                                contentDescription = null,
//                                                colorFilter = ColorFilter.tint(Color(0xff1e293b)),
//                                                modifier = Modifier
//                                                    .align(alignment = Alignment.TopStart)
//                                                    .offset(
//                                                        x = 73.dp,
//                                                        y = 0.dp
//                                                    )
//                                                    .requiredSize(size = 73.dp)
//                                            )
                                        }
                                    },
                                    modifier = Modifier
                                        .requiredWidth(width = 146.dp)
                                        .requiredHeight(height = 73.dp)
                                )
                            }
                        }
                        LazyRow() {
                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .requiredSize(size = 128.dp)
                                ) {
                                    Text(
                                        text = textMo,
                                        color = Color(0xff1e293b),
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(
                                            fontSize = 40.sp,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        modifier = Modifier
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                    )
                                }
                            }
                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .requiredSize(size = 128.dp)
                                ) {
                                    Text(
                                        text = textTu,
                                        color = Color(0xff1e293b),
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(
                                            fontSize = 40.sp,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        modifier = Modifier
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                    )
                                }
                            }
                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .requiredSize(size = 128.dp)
                                ) {
                                    Text(
                                        text = textWe,
                                        color = Color(0xff1e293b),
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(
                                            fontSize = 40.sp,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        modifier = Modifier
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                    )
                                }
                            }
                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .requiredSize(size = 128.dp)
                                ) {
                                    Text(
                                        text = textTh,
                                        color = Color(0xff1e293b),
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(
                                            fontSize = 40.sp,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        modifier = Modifier
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                    )
                                }
                            }
                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .requiredSize(size = 128.dp)
                                ) {
                                    Text(
                                        text = textFr,
                                        color = Color(0xff1e293b),
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(
                                            fontSize = 40.sp,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        modifier = Modifier
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                    )
                                }
                            }
                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .requiredSize(size = 128.dp)
                                ) {
                                    Text(
                                        text = textSa,
                                        color = Color(0xff1e293b),
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(
                                            fontSize = 40.sp,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        modifier = Modifier
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                    )
                                }
                            }
                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .requiredSize(size = 128.dp)
                                ) {
                                    Text(
                                        text = textSu,
                                        color = Color(0xff1e293b),
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(
                                            fontSize = 40.sp,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        modifier = Modifier
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                    )
                                }
                            }
                        }
                        LazyRow() {
                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .requiredSize(size = 128.dp)
                                        .background(color = Color(0xfff1f5f9))
                                        .border(border = BorderStroke(1.dp, Color(0xff94a3b8)))
                                ) {
                                    Text(
                                        text = gridData55_812,
                                        color = Color(0xffcbd5e1),
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(
                                            fontSize = 40.sp
                                        ),
                                        modifier = Modifier
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                    )
                                }
                            }
                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .requiredSize(size = 128.dp)
                                        .background(color = Color(0xfff1f5f9))
                                        .border(border = BorderStroke(1.dp, Color(0xff94a3b8)))
                                ) {
                                    Text(
                                        text = gridData55_814,
                                        color = Color(0xffcbd5e1),
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(
                                            fontSize = 40.sp
                                        ),
                                        modifier = Modifier
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                    )
                                }
                            }
                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .requiredSize(size = 128.dp)
                                        .background(color = Color(0xfff1f5f9))
                                        .border(border = BorderStroke(1.dp, Color(0xff94a3b8)))
                                ) {
                                    Text(
                                        text = gridData55_816,
                                        color = Color(0xffcbd5e1),
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(
                                            fontSize = 40.sp
                                        ),
                                        modifier = Modifier
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                    )
                                }
                            }
                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .requiredSize(size = 128.dp)
                                        .background(color = Color(0xfff1f5f9))
                                        .border(border = BorderStroke(1.dp, Color(0xff94a3b8)))
                                ) {
                                    Text(
                                        text = gridData55_818,
                                        color = Color(0xffcbd5e1),
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(
                                            fontSize = 40.sp
                                        ),
                                        modifier = Modifier
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                    )
                                }
                            }
                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .requiredSize(size = 128.dp)
                                        .background(color = Color(0xfff1f5f9))
                                        .border(border = BorderStroke(1.dp, Color(0xff94a3b8)))
                                ) {
                                    Text(
                                        text = gridData55_820,
                                        color = Color(0xffcbd5e1),
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(
                                            fontSize = 40.sp
                                        ),
                                        modifier = Modifier
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                    )
                                }
                            }
                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .requiredSize(size = 128.dp)
                                        .background(color = Color(0xfff1f5f9))
                                        .border(border = BorderStroke(1.dp, Color(0xff94a3b8)))
                                ) {
                                    Text(
                                        text = gridData55_822,
                                        color = Color(0xffcbd5e1),
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(
                                            fontSize = 40.sp
                                        ),
                                        modifier = Modifier
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                    )
                                }
                            }
                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .requiredSize(size = 128.dp)
                                        .background(color = Color(0xffff8b13))
                                        .border(border = BorderStroke(1.dp, Color(0xff94a3b8)))
                                ) {
                                    Text(
                                        text = gridData55_824,
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(
                                            fontSize = 40.sp
                                        ),
                                        modifier = Modifier
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                    )
                                }
                            }
                        }
                        LazyRow() {
                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .requiredSize(size = 128.dp)
                                        .background(color = Color.White)
                                        .border(border = BorderStroke(1.dp, Color(0xff94a3b8)))
                                ) {
                                    Text(
                                        text = gridData55_937,
                                        color = Color(0xff1e293b),
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(
                                            fontSize = 40.sp
                                        ),
                                        modifier = Modifier
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                    )
                                }
                            }
                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .requiredSize(size = 128.dp)
                                        .background(color = Color.White)
                                        .border(border = BorderStroke(1.dp, Color(0xff94a3b8)))
                                ) {
                                    Text(
                                        text = gridData55_939,
                                        color = Color(0xff1e293b),
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(
                                            fontSize = 40.sp
                                        ),
                                        modifier = Modifier
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                    )
                                }
                            }
                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .requiredSize(size = 128.dp)
                                        .background(color = Color(0xfff1f5f9))
                                        .border(border = BorderStroke(1.dp, Color(0xff94a3b8)))
                                ) {
                                    Text(
                                        text = gridData55_941,
                                        color = Color(0xff1e293b),
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(
                                            fontSize = 40.sp
                                        ),
                                        modifier = Modifier
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                    )
                                }
                            }
                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .requiredSize(size = 128.dp)
                                        .background(color = Color(0xfff1f5f9))
                                        .border(border = BorderStroke(1.dp, Color(0xff94a3b8)))
                                ) {
                                    Text(
                                        text = gridData55_943,
                                        color = Color(0xffcbd5e1),
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(
                                            fontSize = 40.sp
                                        ),
                                        modifier = Modifier
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                    )
                                }
                            }
                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .requiredSize(size = 128.dp)
                                        .background(color = Color(0xfff1f5f9))
                                        .border(border = BorderStroke(1.dp, Color(0xff94a3b8)))
                                ) {
                                    Text(
                                        text = gridData55_945,
                                        color = Color(0xffcbd5e1),
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(
                                            fontSize = 40.sp
                                        ),
                                        modifier = Modifier
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                    )
                                }
                            }
                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .requiredSize(size = 128.dp)
                                        .background(color = Color(0xfff1f5f9))
                                        .border(border = BorderStroke(1.dp, Color(0xff94a3b8)))
                                ) {
                                    Text(
                                        text = gridData55_947,
                                        color = Color(0xffcbd5e1),
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(
                                            fontSize = 40.sp
                                        ),
                                        modifier = Modifier
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                    )
                                }
                            }
                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .requiredSize(size = 128.dp)
                                        .background(color = Color(0xfff1f5f9))
                                        .border(border = BorderStroke(1.dp, Color(0xff94a3b8)))
                                ) {
                                    Text(
                                        text = gridData55_949,
                                        color = Color(0xffcbd5e1),
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(
                                            fontSize = 40.sp
                                        ),
                                        modifier = Modifier
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
        modifier = modifier
            .requiredWidth(width = 1080.dp)
            .requiredHeight(height = 2400.dp)
            .background(color = Color.White)
    )
}

@Preview(widthDp = 1080, heightDp = 2400)
@Composable
private fun HalamanReservasiPreview() {
    LayoutJadwal(Modifier)
}