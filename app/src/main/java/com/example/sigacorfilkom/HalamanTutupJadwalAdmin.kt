package com.example.sigacorfilkom

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HalamanTutupJadwalAdmin(
    kontrolJadwal: KontrolJadwal
) : ViewModel() {
    private val alasan = mutableStateOf("")
    private val kontrolJadwal: KontrolJadwal

    init {
        this.kontrolJadwal = kontrolJadwal
    }

    fun setAlasan(value: String) {
        alasan.value = value
    }

   /**
    *  EVENT submit
    */
    fun submit(
        dateMillis: Long?,
        onSuccess: () -> Unit,
        onFailed: (String) -> Unit
    ) {
        if(dateMillis == null || alasan.value.isEmpty()){
            onFailed("Semua data harus diisi")
        }else{
            kontrolJadwal.tutupJadwal(
                dateMillis,
                alasan.value,
                onSuccess, onFailed
            )
        }
    }

    fun getAlasan() = alasan.value
}