package com.example.sigacorfilkom.boundary_remove_this_later.utama_mahasiswa

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sigacorfilkom.entity_remove_this_later.Perangkat
import com.example.sigacorfilkom.entity_remove_this_later.Reservasi
import com.example.sigacorfilkom.entity_remove_this_later.Sesi
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolJadwal
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolOtentikasi
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolReservasi
import kotlinx.coroutines.launch

class HalamanUtamaMahasiswa : ViewModel() {
//    var reservasiAkhirIni = mutableStateOf<Reservasi?>(null)
//    var sesiAkhirIni = mutableStateOf<Sesi?>(null)
//    var perangkatAkhirIni = mutableStateOf<Perangkat?>(null)
    val namaMahasiswa = mutableStateOf("")
    val tanggal = mutableStateOf("")
    val bulan = mutableStateOf("")
    val tahun = mutableStateOf("")

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
        namaMahasiswa.value = KontrolOtentikasi.getNamaMahasiswa()
        tanggal.value = KontrolJadwal.getHari()[0].getTanggal().toString()
        bulan.value = KontrolJadwal.getHari()[0].getBulan().toString()
        tahun.value = KontrolJadwal.getHari()[0].getTahun().toString()
    }
}