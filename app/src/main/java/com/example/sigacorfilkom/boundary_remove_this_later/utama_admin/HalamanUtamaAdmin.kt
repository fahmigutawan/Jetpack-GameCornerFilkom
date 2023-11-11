package com.example.sigacorfilkom.boundary_remove_this_later.utama_admin

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sigacorfilkom.entity_remove_this_later.Reservasi
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolJadwal
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolReservasi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HalamanUtamaAdmin: ViewModel() {
    val reservasi = mutableStateListOf<Reservasi>()
    val perangkat = mutableMapOf<String, String>()
    init {
        viewModelScope.launch {
            KontrolReservasi.getReservasiTerbaru().collect{
                reservasi.clear()
                reservasi.addAll(it)
            }
        }

        viewModelScope.launch {

            KontrolJadwal.getPerangkat().collect{
                perangkat.clear()
                perangkat.putAll(
                    it.associate { it.getIdPerangkat() to it.getNama() }
                )
            }
        }
    }
}