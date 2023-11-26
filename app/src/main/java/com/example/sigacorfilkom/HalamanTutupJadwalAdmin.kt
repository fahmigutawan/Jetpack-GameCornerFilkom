package com.example.sigacorfilkom

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId

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
        /**
         *  CALL statusKosong = cekKosong
         */
        /**
         *  OPT statusKosong = true
         */
        if (dateMillis == null || alasan.value.isEmpty()) {
            /**
             *  CALL tampilkan error kosong
             */
            onFailed("Semua data harus diisi")
        } else {
            val instant = Instant.ofEpochMilli(dateMillis)
            val localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate()
            val tanggal = localDate.dayOfMonth
            val bulan = localDate.monthValue
            val tahun = localDate.year

            /**
             *  CALL   tutupJadwal()
             *  TUJUAN (C) KontrolJadwal
             */
            viewModelScope.launch {
                kontrolJadwal.tutupJadwal(
                    tanggal,
                    bulan,
                    tahun,
                    alasan.value,
                    onSuccess, onFailed
                )
            }
        }
    }

    fun getAlasan() = alasan.value
}