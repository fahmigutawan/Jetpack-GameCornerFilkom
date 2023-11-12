package com.example.sigacorfilkom

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HalamanHistoryMahasiswa : ViewModel() {
    private val reservasiBelumLewat = mutableStateListOf<Reservasi>()
    private val perangkat = mutableMapOf<String, String>()

    init {
        viewModelScope.launch {
            KontrolReservasi.getReservasiTerbaruForMahasiswa().collect{
                reservasiBelumLewat.clear()
                reservasiBelumLewat.addAll(it)
            }
        }

        viewModelScope.launch {
            KontrolJadwal.getPerangkat().collect{
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