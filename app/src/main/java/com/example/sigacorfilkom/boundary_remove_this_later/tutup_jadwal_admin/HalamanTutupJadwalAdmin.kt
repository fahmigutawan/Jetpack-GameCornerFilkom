package com.example.sigacorfilkom.boundary_remove_this_later.tutup_jadwal_admin

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolJadwal
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolNavigasi
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolSnackbar
import java.util.Date

class HalamanTutupJadwalAdmin(
    kontrolJadwal: KontrolJadwal,
    kontrolNavigasi: KontrolNavigasi,
    kontrolSnackbar: KontrolSnackbar
) : ViewModel() {
    private val alasan = mutableStateOf("")
    private val kontrolJadwal:KontrolJadwal
    private val kontrolSnackbar:KontrolSnackbar

    init {
        this.kontrolSnackbar = kontrolSnackbar
    }

    init {
        this.kontrolJadwal = kontrolJadwal
    }

    fun setAlasan(value: String) {
        alasan.value = value
    }

    fun validate(dateMillis: Long?) = dateMillis != null && alasan.value.isNotEmpty()

    fun tutupJadwal(
        dateMillis: Long?,
    ) {
        if(!validate(dateMillis)){
            kontrolSnackbar.showSnackbar("Semua data harus diisi")
        }else{
            dateMillis?.let {
                kontrolJadwal.tutupJadwal(
                    it,
                    alasan.value
                )
            }
        }
    }

    fun getAlasan() = alasan.value
}