package com.example.sigacorfilkom.boundary_remove_this_later.jadwal

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.sigacorfilkom.SnackbarHandler
import kotlin.math.ceil


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutJadwal(
    viewModel: HalamanJadwal
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
    val width = LocalConfiguration.current.screenWidthDp - 32
    val showBerhasilDialog = remember {
        mutableStateOf(false)
    }

    if (showBerhasilDialog.value) {
        AlertDialog(
            onDismissRequest = { /*TODO*/ },
            confirmButton = {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xffFF9E3A),
                        contentColor = Color.White
                    ),
                    onClick = {
//                        navController.navigate("home_mahasiswa") {
//                            popUpTo(navController.graph.id) {
//                                inclusive = true
//                            }
//                        }
                    }
                ) {
                    Text(text = "OK")
                }
            },
            title = {
                Text(
                    text = "Berhasil"
                )
            },
            text = {
                Text(text = "Anda telah melakukan reservasi! Mohon datang ke Game Corner dengan menunjukkan KTM pada waktu yang dipilih")
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        )
    }

    LaunchedEffect(key1 = viewModel.getPickedHari().value) {
        viewModel.getPickedHari().value?.let {
            viewModel.setPickedPerangkat(null)
        }
    }

    LaunchedEffect(key1 = viewModel.getPickedPerangkat().value) {
        viewModel.getPickedPerangkat().value?.let {
            viewModel.setPickedSesi(null)
            viewModel.loadSesi()
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Reservasi")
                }
            )
        },
        bottomBar = {
            BottomAppBar {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onClick = {
                        viewModel.reservasi(
                            onSuccess = {
                                showBerhasilDialog.value = true
                            },
                            onFailed = {
                                SnackbarHandler.showSnackbar(it)
                            }
                        )
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xffFF9E3A),
                        contentColor = Color.White
                    ),
                    enabled = viewModel.getPickedHari().value != null && viewModel.getPickedPerangkat().value != null && viewModel.getPickedSesi().value != null
                ) {
                    Text(text = "Booking")
                }
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding(),
                    bottom = it.calculateBottomPadding()
                )
                .padding(horizontal = 16.dp)
        ) {
            item {
                Text(
                    text = "Pilih Tanggal",
                    color = Color(0xffFF9E3A),
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 2.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        viewModel.getListHari().forEach {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable(enabled = !it.getIsDitutup()) {
                                        viewModel.setPickedHari(it)
                                    },
                                border = if (viewModel.getPickedHari().value == it) BorderStroke(
                                    width = 3.dp,
                                    color = Color(0xffFF9E3A)
                                ) else BorderStroke(
                                    width = 1.dp,
                                    color = Color(0xFFB6B6B6)
                                ),
                                colors = CardDefaults.cardColors(
                                    containerColor = if (!it.getIsDitutup()) Color(0xffffffff) else Color(
                                        0xFFDBD9D9
                                    ),
                                    contentColor = if (!it.getIsDitutup()) Color(0xff000000) else Color(
                                        0xFF919090
                                    )
                                )
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Text(
                                        text = "${it.getTanggal()} ${monthMapper[it.getBulan()] ?: "..."} ${it.getTahun()}"
                                    )

                                    if (it.getIsDitutup()) {
                                        Text(
                                            text = it.getAlasanDitutup(),
                                            color = Color.Red,
                                            fontSize = 10.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Pilih Device", color = Color(0xffFF9E3A), fontWeight = FontWeight.Bold)
                if (viewModel.getPickedHari().value == null) {
                    Text(
                        text = "Pilih tanggal terlebih dahulu!",
                        color = Color.Red,
                        fontSize = 10.sp
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                val maxRow = remember {
                    derivedStateOf {
                        ceil(viewModel.getListPerangkat().size / 3.0)
                    }
                }
                Column {
                    for (i in 0..<maxRow.value.toInt()) {
                        Row {
                            for (j in 0..2) {
                                if (j + (i * 3) < viewModel.getListPerangkat().size) {
                                    Box(
                                        modifier = Modifier
                                            .width((width / 3).dp)
                                            .height((width / 3 / 1.6).dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(4.dp)
                                                .border(
                                                    width = if (viewModel.getPickedPerangkat().value == viewModel
                                                            .getListPerangkat()[j + (i * 3)]
                                                    ) 3.dp else 1.dp,
                                                    color = if (viewModel.getPickedPerangkat().value == viewModel
                                                            .getListPerangkat()[j + (i * 3)]
                                                    ) Color(0xffFF9E3A) else Color(0xFFB6B6B6),
                                                    shape = RoundedCornerShape(8.dp)
                                                )
                                                .clickable(
                                                    enabled = viewModel.getPickedHari().value != null
                                                ) {
                                                    viewModel.setPickedPerangkat(
                                                        viewModel
                                                            .getListPerangkat()[j + (i * 3)]
                                                    )
                                                }
                                                .background(
                                                    if (viewModel.getPickedHari().value != null) Color(
                                                        0xffffffff
                                                    ) else Color(0xFFDBD9D9),
                                                    shape = RoundedCornerShape(8.dp)
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = viewModel.getListPerangkat()[j + (i * 3)]
                                                    .getNama(),
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Pilih Sesi", color = Color(0xffFF9E3A), fontWeight = FontWeight.Bold)
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                when {
                    viewModel.getPickedPerangkat().value != null && viewModel.getListSesi()
                        .isEmpty() -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 64.dp), contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    (viewModel.getPickedPerangkat().value == null || viewModel.getPickedHari().value == null) -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp), contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Anda Belum Memilih Tanggal dan/atau Perangkat.")
                        }
                    }

                    else -> {
                        val maxRow = remember {
                            derivedStateOf {
                                ceil(viewModel.getListSesi().size / 3.0)
                            }
                        }
                        Column {
                            for (i in 0..<maxRow.value.toInt()) {
                                Row {
                                    for (j in 0..2) {
                                        if (j + (i * 3) < viewModel.getListSesi().size) {
                                            Box(
                                                modifier = Modifier
                                                    .width((width / 3).dp)
                                                    .height((width / 3 / 1.6).dp),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Box(
                                                    modifier = Modifier
                                                        .fillMaxSize()
                                                        .padding(4.dp)
                                                        .border(
                                                            width = if (viewModel.getPickedSesi().value == viewModel
                                                                    .getListSesi()[j + (i * 3)]
                                                            ) 3.dp else 1.dp,
                                                            color = if (viewModel.getPickedSesi().value == viewModel
                                                                    .getListSesi()[j + (i * 3)]
                                                            ) Color(0xffFF9E3A) else Color(
                                                                0xFFB6B6B6
                                                            ),
                                                            shape = RoundedCornerShape(8.dp)
                                                        )
                                                        .background(
                                                            if (viewModel.getListSesi()[j + (i * 3)].getBooked())
                                                                Color(0xFFD8D5D5) else Color(
                                                                0xffffffff
                                                            ),
                                                            shape = RoundedCornerShape(8.dp)
                                                        )
                                                        .clickable(
                                                            enabled = !viewModel.getListSesi()[j + (i * 3)].getBooked()
                                                        ) {
                                                            viewModel.setPickedSesi(
                                                                viewModel.getListSesi()[j + (i * 3)]
                                                            )
                                                        },
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    Column(modifier = Modifier.padding(8.dp)) {
                                                        Text(
                                                            text = "Sesi ${
                                                                viewModel
                                                                    .getListSesi()[j + (i * 3)]
                                                                    .getSesiNumber()
                                                            }",
                                                            fontWeight = FontWeight.Bold
                                                        )
                                                        Text(
                                                            text = viewModel
                                                                .getListSesi()[j + (i * 3)].getWaktu()
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
