package com.example.sigacorfilkom.boundary_remove_this_later.tutup_jadwal_admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.sigacorfilkom.SnackbarHandler

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutTutupJadwalAdmin(
    viewModel: HalamanTutupJadwalAdmin
) {
    val datePickerState = rememberDatePickerState()

    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            DatePicker(
                title = {
                    Text(
                        text = "Pilih Tanggal",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xffFF9E3A)
                    )
                },
                headline = {
                    Text(text = "Pilih tanggal yang ingin ditutup")
                },
                modifier = Modifier.fillMaxWidth(),
                state = datePickerState
            )
        }
        item {
            Text(
                text = "Alasan Ditutup",
                fontWeight = FontWeight.Bold,
                color = Color(0xffFF9E3A)
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp),
                value = viewModel.getAlasan(),
                onValueChange = { viewModel.setAlasan(it) },
                placeholder = {
                    Text(text = "Masukkan alasan jadwal ditutup")
                }
            )
        }

        item {
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    viewModel.tutupJadwal(
                        datePickerState.selectedDateMillis,
                        onSuccess = {
                            SnackbarHandler.showSnackbar("Tanggal berhasil ditutup")
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
            ) {
                Text(text = "Tutup")
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}