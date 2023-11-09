package com.example.sigacorfilkom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LayoutPanduan(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 1080.dp)
            .requiredHeight(height = 2400.dp)
            .background(color = Color.White)
    ) {
        Text(
            lineHeight = 3.sp,
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = Color.Black,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold)) {append("Pilih icon “+”\nPilih Tanggal ")}
                withStyle(style = SpanStyle(
                    color = Color.Black,
                    fontSize = 40.sp)) {append("tanggal yang tersedia hanya untuk 3 hari ke depan\n")}
                withStyle(style = SpanStyle(
                    color = Color.Black,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold)) {append("Pilih Device ")}
                withStyle(style = SpanStyle(
                    color = Color.Black,
                    fontSize = 40.sp)) {append("tersedia 6 device yang bisa kamu pilih :  2 PS5, 2 XBOX, 1 PC, 1 Pump it \n")}
                withStyle(style = SpanStyle(
                    color = Color.Black,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold)) {append("Pilih Sesi ")}
                withStyle(style = SpanStyle(
                    color = Color.Black,
                    fontSize = 40.sp)) {append("pilih 1 dari 8 sesi yang ada dari pukul 08.00 - 17.45\n")}
                withStyle(style = SpanStyle(
                    color = Color.Black,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold)) {append("Klik Booking ")}
                withStyle(style = SpanStyle(
                    color = Color.Black,
                    fontSize = 40.sp)) {append("muncul notifikasi bahwa reservasimu berhasil\n")}
                withStyle(style = SpanStyle(
                    color = Color.Black,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold)) {append("Bawa KTM ke Lokasi ")}
                withStyle(style = SpanStyle(
                    color = Color.Black,
                    fontSize = 40.sp)) {append("jangan lupa membawa KTM untuk ditunjukkan ke petugas, ya!")}},
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(x = 0.dp,
                    y = 304.dp)
                .requiredWidth(width = 844.dp))
        LazyRow(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 29.dp,
                    y = 2190.dp)
                .requiredWidth(width = 1022.dp)
                .requiredHeight(height = 144.dp)
                .clip(shape = RoundedCornerShape(24.dp))
                .background(color = Color(0xffff8b13))
        ) {
            item {
                Text(
                    text = "OK",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .requiredWidth(width = 228.dp)
                        .requiredHeight(height = 72.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically))
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
                    text = "Panduan Reservasi Game Corner",
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
        }
    }
}

@Preview(widthDp = 1080, heightDp = 2400)
@Composable
private fun HalamanPanduanPreview() {
    LayoutPanduan(Modifier)
}