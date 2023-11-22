package com.example.sigacorfilkom.boundary_remove_this_later.utama_mahasiswa

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sigacorfilkom.boundary_remove_this_later.otentikasi.Otentikasi
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolJadwal
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolUtamaMahasiswa
import kotlinx.coroutines.launch
import java.time.LocalDate

class HalamanUtamaMahasiswa(
    kontrolJadwal: KontrolJadwal,
    kontrolUtamaMahasiswa: KontrolUtamaMahasiswa
) : ViewModel() {

    private val namaMahasiswa = mutableStateOf("")
    private val tanggal = mutableStateOf("")
    private val bulan = mutableStateOf("")
    private val tahun = mutableStateOf("")
    private val kontrolJadwal: KontrolJadwal
    private val kontrolUtamaMahasiswa: KontrolUtamaMahasiswa

    init {
        this.kontrolJadwal = kontrolJadwal
        this.kontrolUtamaMahasiswa = kontrolUtamaMahasiswa

        namaMahasiswa.value = Otentikasi.getMahasiswa()!!.getNama()

        val now = LocalDate.now()
        tanggal.value = now.dayOfMonth.toString()
        bulan.value = now.month.value.toString()
        tahun.value = now.year.toString()
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
        kontrolUtamaMahasiswa.logout()
    }
}