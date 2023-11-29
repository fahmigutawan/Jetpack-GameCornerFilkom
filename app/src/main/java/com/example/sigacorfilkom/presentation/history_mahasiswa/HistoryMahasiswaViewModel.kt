package com.example.sigacorfilkom.presentation.history_mahasiswa

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sigacorfilkom.model.Reservasi
import com.example.sigacorfilkom.repoJadwal
import com.example.sigacorfilkom.repoReservasi
import kotlinx.coroutines.launch

class HistoryMahasiswaViewModel : ViewModel() {
    private val reservasiBelumLewat = mutableStateListOf<Reservasi>()
    private val perangkat = mutableMapOf<String, String>()

    init {
        viewModelScope.launch {
            repoReservasi.getReservasiTerbaruForMahasiswa().collect{
                reservasiBelumLewat.clear()
                reservasiBelumLewat.addAll(it)
            }
        }

        viewModelScope.launch {
            repoJadwal.getPerangkat().collect{
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