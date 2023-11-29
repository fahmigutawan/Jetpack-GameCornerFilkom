package com.example.sigacorfilkom.presentation.utama_mahasiswa

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sigacorfilkom.repoJadwal
import com.example.sigacorfilkom.repoOtentikasi

class UtamaMahasiswaViewModel : ViewModel() {
//    var reservasiAkhirIni = mutableStateOf<Reservasi?>(null)
//    var sesiAkhirIni = mutableStateOf<Sesi?>(null)
//    var perangkatAkhirIni = mutableStateOf<Perangkat?>(null)
    private val namaMahasiswa = mutableStateOf("")
    private val tanggal = mutableStateOf("")
    private val bulan = mutableStateOf("")
    private val tahun = mutableStateOf("")

    fun getNamaMahasiswa() = namaMahasiswa.value

    fun getTanggal() = tanggal.value

    fun getBulan() = bulan.value

    fun getTahun() = tahun.value

//    fun initData() {
//        viewModelScope.launch {
//            KontrolReservasi.getReservasiAkhirIni().collect {
//                reservasiAkhirIni.value = it
//            }
//        }
//
//        namaMahasiswa.value = KontrolOtentikasi.getNamaMahasiswa()
//        tanggal.value = KontrolJadwal.getHari()[0].getTanggal().toString()
//        bulan.value = KontrolJadwal.getHari()[0].getBulan().toString()
//        tahun.value = KontrolJadwal.getHari()[0].getTahun().toString()
//    }
//
//    fun loadSesi() {
//        viewModelScope.launch {
//            reservasiAkhirIni.value?.let {
//                KontrolJadwal.getSesiByNomorSesi(it.getNomorSesi()).collect {
//                    sesiAkhirIni.value = it
//                }
//            }
//        }
//    }
//
//    fun loadPerangkat(){
//        viewModelScope.launch {
//            reservasiAkhirIni.value?.let {
//                KontrolJadwal.getPerangkatById(it.getIdPerangkat()).collect {
//                    perangkatAkhirIni.value = it
//                }
//            }
//        }
//    }

    init {
        namaMahasiswa.value = repoOtentikasi.getNamaMahasiswa()
        tanggal.value = repoJadwal.getHari()[0].getTanggal().toString()
        bulan.value = repoJadwal.getHari()[0].getBulan().toString()
        tahun.value = repoJadwal.getHari()[0].getTahun().toString()
    }
}