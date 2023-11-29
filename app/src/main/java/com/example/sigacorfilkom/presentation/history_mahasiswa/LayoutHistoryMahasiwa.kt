package com.example.sigacorfilkom.presentation.history_mahasiswa

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutHistoryMahasiswa(navController: NavController) {
    val viewModel = viewModel<HistoryMahasiswaViewModel>()
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
                    Text(text = "Reservasi Terkini")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "")
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
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            items(viewModel.getReservasiBelumLewat()){item ->
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 2.dp
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = viewModel.getPerangkat()[item.getIdPerangkat()] ?: "...",
                                fontWeight = FontWeight.Bold
                            )
                            Row {
                                Text(text = "Sesi ${item.getNomorSesi()} | ")
                                Text(text = "${item.getLocalDate().dayOfMonth} ${monthMapper[item.getLocalDate().monthValue]} ${item.getLocalDate().year}")
                            }
                            Text(text = "NIM. ${item.getNimPeminjam()}")
                        }

                        Text(
                            textAlign = TextAlign.End,
                            modifier = Modifier.weight(1f),
                            text = item.getStatus(),
                            color = when (item.getStatus()) {
                                "Menunggu" -> Color(0xffFF9E3A)
                                "Gagal" -> Color.Red
                                "Divalidasi" -> Color(0xFF176800)
                                else -> Color(0xffFF9E3A)
                            },
                            fontWeight = FontWeight.Bold
                        )
                    }

                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}