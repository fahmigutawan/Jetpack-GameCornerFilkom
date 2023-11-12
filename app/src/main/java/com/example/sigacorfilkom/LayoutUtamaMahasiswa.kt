package com.example.sigacorfilkom

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LayoutUtamaMahasiswa(navController: NavController) {
    val viewModel = viewModel<HalamanUtamaMahasiswa>()
    val monthMapper = mapOf(
        1 to "Januari",
        2 to "Februari",
        3 to "Maret",
        4 to "April",
        5 to "Mei",
        6 to "Juni",
        7 to "Juli",
        8 to "Agustus",
        9 to "September",
        10 to "Oktober",
        11 to "November",
        12 to "Desember"
    )
    
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "SiGACOR")
                },
                actions = {
                    IconButton(
                        onClick = {
                            KontrolOtentikasi.logout()
                            navController.navigate("login") {
                                popUpTo(navController.graph.id) { inclusive = true }
                            }
                        }
                    ) {
                        Icon(
                            painter = rememberAsyncImagePainter(model = R.drawable.ic_logout),
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Hi, ${viewModel.getNamaMahasiswa()}",
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xffFF9E3A)
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "${viewModel.getTanggal()} ${monthMapper[viewModel.getBulan().toInt()] ?: viewModel.getBulan()} ${viewModel.getTahun()}",
                        textAlign = TextAlign.End,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Menu",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xffFF9E3A)
                    )

                    ElevatedCard(
                        elevation = CardDefaults.elevatedCardElevation(
                            defaultElevation = 2.dp
                        ),
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        onClick = {
                            navController.navigate("panduan_mahasiswa")
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(84.dp),
                                model = R.drawable.ic_panduan,
                                contentDescription = ""
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Column {
                                Text(
                                    text = "Buku Panduan",
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xffFF9E3A)
                                )
                                Text(
                                    text = "Panduan melakukan reservasi game corner lengkap",
                                    color = Color(0xffFF9E3A)
                                )
                            }
                        }
                    }

                    ElevatedCard(
                        elevation = CardDefaults.elevatedCardElevation(
                            defaultElevation = 2.dp
                        ),
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        onClick = {
                            navController.navigate("jadwal_mahasiswa")
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(84.dp),
                                model = R.drawable.ic_jadwal,
                                contentDescription = ""
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Column {
                                Text(
                                    text = "Lihat Jadwal dan Reservasi",
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xffFF9E3A)
                                )
                                Text(
                                    text = "Jadwal reservasi game corner terbaru",
                                    color = Color(0xffFF9E3A)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}