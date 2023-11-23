package com.example.sigacorfilkom.boundary_remove_this_later.utama_admin

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.sigacorfilkom.R
import com.example.sigacorfilkom.SnackbarHandler
import com.example.sigacorfilkom.boundary_remove_this_later.tutup_jadwal_admin.HalamanTutupJadwalAdmin
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolJadwal
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolOtentikasi
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolReservasi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutUtamaAdmin(
    viewModel: HalamanUtamaAdmin
) {
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

    if (viewModel.getPickedReservasi() != null) {
        AlertDialog(
            onDismissRequest = {
                viewModel.setPickedReservasi(null)
            },
            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color(0xffFF9E3A)
                        ),
                        border = BorderStroke(3.dp, Color(0xffFF9E3A)),
                        onClick = {
                            viewModel.ubahStatusReservasi(
                                viewModel.getPickedReservasi()?.getReservasiId() ?: "",
                                "Gagal",
                                onSuccess = {
                                    SnackbarHandler.showSnackbar("Berhasil merubah status")
                                    viewModel.loadReservasi()
                                    viewModel.setPickedReservasi(null)
                                },
                                onFailed = {
                                    SnackbarHandler.showSnackbar(it)
                                    viewModel.setPickedReservasi(null)
                                }
                            )
                        }
                    ) {
                        Text(text = "Salah")
                    }

                    Button(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xffFF9E3A),
                            contentColor = Color.White
                        ),
                        onClick = {
                            viewModel.ubahStatusReservasi(
                                viewModel.getPickedReservasi()?.getReservasiId() ?: "",
                                "Divalidasi",
                                onSuccess = {
                                    SnackbarHandler.showSnackbar("Berhasil merubah status")
                                    viewModel.loadReservasi()
                                    viewModel.setPickedReservasi(null)
                                },
                                onFailed = {
                                    SnackbarHandler.showSnackbar(it)
                                    viewModel.setPickedReservasi(null)
                                }
                            )
                        }
                    ) {
                        Text(text = "Benar")
                    }
                }
            },
            title = {
                Text(
                    text = "Validasi Mahasiswa"
                )
            },
            text = {
                Text(text = "Apakah benar identitas mahasiswa tersebut?")
            },
            properties = DialogProperties(
                dismissOnClickOutside = true,
                dismissOnBackPress = true
            )
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Admin SiGACOR")
                },
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.logout()
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
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(viewModel.getReservasi()) { item ->
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        viewModel.setPickedReservasi(item)
                    },
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
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}