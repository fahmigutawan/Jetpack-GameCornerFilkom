package com.example.sigacorfilkom.boundary_remove_this_later.utama_admin

import android.util.Log
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.sigacorfilkom.R
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolJadwal
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolOtentikasi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutUtamaAdmin(navController: NavController) {
    val viewModel = viewModel<HalamanUtamaAdmin>()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Admin SiGACOR")
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
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(viewModel.reservasi) { item ->
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { /*TODO*/ },
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
                                text = viewModel.perangkat[item.getIdPerangkat()] ?: "...",
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = "Sesi ${item.getNomorSesi()}")
                            Text(text = "NIM. ${item.getNimPeminjam()}")
                        }

                        Text(
                            textAlign = TextAlign.End,
                            modifier = Modifier.weight(1f),
                            text = item.getStatus(),
                            color = Color(0xffFF9E3A),
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