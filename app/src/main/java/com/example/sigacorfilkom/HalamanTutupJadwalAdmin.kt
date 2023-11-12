package com.example.sigacorfilkom

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HalamanTutupJadwalAdmin : ViewModel() {
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
            KontrolJadwal.tutupJadwal(
                dateMillis,
                alasan.value,
                onSuccess, onFailed
            )
        }
    }

    fun getAlasan() = alasan.value
}