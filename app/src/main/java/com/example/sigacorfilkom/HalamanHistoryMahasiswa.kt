package com.example.sigacorfilkom

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HalamanHistoryMahasiswa(
    kontrolJadwal: KontrolJadwal,
    kontrolReservasi: KontrolReservasi
) :
    ViewModel() {
    private val daftarReservasi = mutableStateListOf<Reservasi>()
    private val daftarNamaPerangkat = mutableMapOf<String, String>()
    private var kontrolReservasi: KontrolReservasi
    private var kontrolJadwal: KontrolJadwal

    init {
        this.kontrolJadwal = kontrolJadwal
        this.kontrolReservasi = kontrolReservasi
    }

    init {
        viewModelScope.launch {
//            kontrolReservasi.getReservasiTerbaruForMahasiswa().collect {
//                reservasiBelumLewat.clear()
//                reservasiBelumLewat.addAll(it)
//            }
        }
    }

    fun setDaftarNamaPerangkat(daftarPerangkat: List<Perangkat>) {
        daftarNamaPerangkat.clear()
        daftarNamaPerangkat.putAll(daftarPerangkat.associate { perangkat ->
            perangkat.getIdPerangkat() to perangkat.getNama()
        })
    }

    fun setDaftarReservasi(daftarReservasi: List<Reservasi>) {
        this.daftarReservasi.clear()
        this.daftarReservasi.addAll(daftarReservasi)
    }

    fun getReservasiBelumLewat() = daftarReservasi

    fun getPerangkat() = daftarNamaPerangkat
}