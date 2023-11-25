package com.example.sigacorfilkom.boundary_remove_this_later.tutup_jadwal_admin

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolJadwal
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolNavigasi
import java.util.Date

class HalamanTutupJadwalAdmin(
    kontrolJadwal: KontrolJadwal,
    kontrolNavigasi: KontrolNavigasi
) : ViewModel() {
    private val alasan = mutableStateOf("")
    private val kontrolJadwal:KontrolJadwal

    init {
        this.kontrolJadwal = kontrolJadwal
    }

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
            kontrolJadwal.tutupJadwal(
                dateMillis,
                alasan.value,
                onSuccess, onFailed
            )
        }
    }

    fun getAlasan() = alasan.value
}