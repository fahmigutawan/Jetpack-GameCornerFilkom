package com.example.sigacorfilkom

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LayoutPanduan(
    viewModel: HalamanPanduan
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = "Panduan Reservasi Game Corner")
            })
        },
        bottomBar = {
            BottomAppBar {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onClick = {
//                        navController.popBackStack()
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xffFF9E3A),
                        contentColor = Color.White
                    ),
                ) {
                    Text(text = "OK")
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding())
                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row {
                Text(text = "1. ")
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(text = "Pilih Menu \"Lihat Jadwal dan Reservasi\"", fontWeight = FontWeight.Bold)
                }
            }

            Row {
                Text(text = "2. ")
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(text = "Pilih Tanggal", fontWeight = FontWeight.Bold)
                    Text(text = "Tanggal yang tersedia hanya untuk 3 hari ke depan (termasuk hari ini)")
                }
            }

            Row {
                Text(text = "3. ")
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(text = "Pilih Device", fontWeight = FontWeight.Bold)
                    Text(text = "Tersedia device yang bisa kamu pilih")
                }
            }

            Row {
                Text(text = "4. ")
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(text = "Pilih Sesi", fontWeight = FontWeight.Bold)
                    Text(text = "Pilih sesi sesuai dengan yang kamu inginkan")
                }
            }

            Row {
                Text(text = "5. ")
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(text = "Klik Booking", fontWeight = FontWeight.Bold)
                    Text(text = "Data kamu dikirimkan dan sudah tersimpan")
                }
            }

            Row {
                Text(text = "6. ")
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(text = "Bawa KTM ke Lokasi", fontWeight = FontWeight.Bold)
                    Text(text = "Jangan lupa membawa KTM untuk ditunjukkan ke petugas, ya!")
                }
            }
        }
    }
}