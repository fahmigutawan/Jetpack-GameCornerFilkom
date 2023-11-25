package com.example.sigacorfilkom.boundary_remove_this_later.utama_mahasiswa

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sigacorfilkom.boundary_remove_this_later.otentikasi.Otentikasi
import com.example.sigacorfilkom.KontrolJadwal
import com.example.sigacorfilkom.KontrolPanduan
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolUtamaMahasiswa
import kotlinx.coroutines.launch
import java.time.LocalDate

class HalamanUtamaMahasiswa(
    kontrolJadwal: KontrolJadwal,
    kontrolUtamaMahasiswa: KontrolUtamaMahasiswa,
    kontrolPanduan: KontrolPanduan
) : ViewModel() {

    private val namaMahasiswa = mutableStateOf("")
    private val tanggal = mutableStateOf("")
    private val bulan = mutableStateOf("")
    private val tahun = mutableStateOf("")

    private val kontrolJadwal: KontrolJadwal
    private val kontrolUtamaMahasiswa: KontrolUtamaMahasiswa
    private val kontrolPanduan: KontrolPanduan

    init {
        this.kontrolJadwal = kontrolJadwal
        this.kontrolUtamaMahasiswa = kontrolUtamaMahasiswa
        this.kontrolPanduan = kontrolPanduan

        namaMahasiswa.value = Otentikasi.getMahasiswa()!!.getNama()

        val now = LocalDate.now()
        tanggal.value = now.dayOfMonth.toString()
        bulan.value = now.month.value.toString()
        tahun.value = now.year.toString()
    }

    /**
     *  EVENT lihatJadwal
     */
    fun lihatJadwal() {
        viewModelScope.launch {
            /**
             *  CALL   tampilkanHalamanJadwal()
             *  TUJUAN (C) KontrolJadwal
             */
            kontrolJadwal.tampilkanHalamanJadwal()
        }
    }

    /**
     *  EVENT lihatPanduan
     */
    fun lihatPanduan() {
        kontrolPanduan.tampilkanHalamanPanduan()
    }

    fun getNamaMahasiswa() = namaMahasiswa.value

    fun getTanggal() = tanggal.value

    fun getBulan() = bulan.value

    fun getTahun() = tahun.value

    fun logout() {
        kontrolUtamaMahasiswa.logout()
    }
}