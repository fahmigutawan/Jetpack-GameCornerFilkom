package com.example.sigacorfilkom.boundary_remove_this_later.utama_mahasiswa

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolJadwal
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolNavigasi
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolOtentikasi

class HalamanUtamaMahasiswa(
    kontrolJadwal: KontrolJadwal,
    kontrolOtentikasi: KontrolOtentikasi,
    kontrolNavigasi: KontrolNavigasi
) : ViewModel() {
    private val namaMahasiswa = mutableStateOf("")
    private val tanggal = mutableStateOf("")
    private val bulan = mutableStateOf("")
    private val tahun = mutableStateOf("")
    private val kontrolJadwal:KontrolJadwal
    private val kontrolOtentikasi:KontrolOtentikasi
    private val kontrolNavigasi:KontrolNavigasi

    init {
        this.kontrolJadwal = kontrolJadwal
        this.kontrolOtentikasi = kontrolOtentikasi
        this.kontrolNavigasi = kontrolNavigasi
    }

    fun getNamaMahasiswa() = namaMahasiswa.value

    fun getTanggal() = tanggal.value

    fun getBulan() = bulan.value

    fun getTahun() = tahun.value

    fun logout(){
        kontrolOtentikasi.logout()
    }

    fun navigasiKeHalamanLogin() = kontrolNavigasi.navigasiKeHalamanLogin()

    fun navigasiKePanduan() = kontrolNavigasi.navigasiKePanduan()

    fun navigasiKeJadwal() = kontrolNavigasi.navigasiKeJadwal()

    init {
        namaMahasiswa.value = kontrolOtentikasi.getNamaMahasiswa()
        tanggal.value = kontrolJadwal.getHari()[0].getTanggal().toString()
        bulan.value = kontrolJadwal.getHari()[0].getBulan().toString()
        tahun.value = kontrolJadwal.getHari()[0].getTahun().toString()
    }
}