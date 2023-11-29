package com.example.sigacorfilkom.presentation.tutup_jadwal_admin

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sigacorfilkom.repoJadwal

class TutupJadwalAdminViewModel : ViewModel() {
    private val alasan = mutableStateOf("")

    fun setAlasan(value: String) {
        alasan.value = value
    }

    fun tutupJadwal(
        dateMillis: Long?,
        onSuccess: () -> Unit,
        onFailed: (String) -> Unit
    ) {
        if(dateMillis == null || alasan.value.isEmpty()){
            onFailed("Semua data harus diisi")
        }else{
            repoJadwal.tutupJadwal(
                dateMillis,
                alasan.value,
                onSuccess, onFailed
            )
        }
    }

    fun getAlasan() = alasan.value
}