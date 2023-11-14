package com.example.sigacorfilkom.boundary_remove_this_later.history_mahasiswa

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sigacorfilkom.entity_remove_this_later.Reservasi
import com.example.sigacorfilkom.kontrolJadwal
import com.example.sigacorfilkom.kontrolReservasi
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolJadwal
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolReservasi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HalamanHistoryMahasiswa : ViewModel() {
    private val reservasiBelumLewat = mutableStateListOf<Reservasi>()
    private val perangkat = mutableMapOf<String, String>()

    init {
        viewModelScope.launch {
            kontrolReservasi.getReservasiTerbaruForMahasiswa().collect{
                reservasiBelumLewat.clear()
                reservasiBelumLewat.addAll(it)
            }
        }

        viewModelScope.launch {
            kontrolJadwal.getPerangkat().collect{
                perangkat.clear()
                perangkat.putAll(it.associate {
                    it.getIdPerangkat() to it.getNama()
                })
            }
        }
    }

    fun getReservasiBelumLewat() = reservasiBelumLewat

    fun getPerangkat() = perangkat
}