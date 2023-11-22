package com.example.sigacorfilkom.boundary_remove_this_later.utama_mahasiswa

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolJadwal
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolOtentikasi
import kotlinx.coroutines.launch
import java.time.LocalDate

class HalamanUtamaMahasiswa(
    kontrolJadwal: KontrolJadwal,
    kontrolOtentikasi: KontrolOtentikasi
) : ViewModel() {
    private val namaMahasiswa = mutableStateOf("")
    private val tanggal = mutableStateOf("")
    private val bulan = mutableStateOf("")
    private val tahun = mutableStateOf("")
    private val kontrolJadwal: KontrolJadwal
    private val kontrolOtentikasi: KontrolOtentikasi

    init {
        this.kontrolJadwal = kontrolJadwal
        this.kontrolOtentikasi = kontrolOtentikasi
    }

    fun lihatJadwal() {
        viewModelScope.launch {
            kontrolJadwal.tampilkanHalamanJadwal()
        }
    }

    fun getNamaMahasiswa() = namaMahasiswa.value

    fun getTanggal() = tanggal.value

    fun getBulan() = bulan.value

    fun getTahun() = tahun.value

    fun logout() {
        kontrolOtentikasi.logout()
    }

    init {
        namaMahasiswa.value = kontrolOtentikasi.getNamaMahasiswa()

        val now = LocalDate.now()
        tanggal.value = now.dayOfMonth.toString()
        bulan.value = now.month.value.toString()
        tahun.value = now.year.toString()
    }
}