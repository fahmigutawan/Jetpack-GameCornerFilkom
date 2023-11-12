package com.example.sigacorfilkom

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HalamanUtamaAdmin: ViewModel() {
    private val reservasi = mutableStateListOf<Reservasi>()
    private val perangkat = mutableMapOf<String, String>()
    private val pickedReservasi = mutableStateOf<Reservasi?>(null)

    fun loadReservasi(){
        viewModelScope.launch {
            KontrolReservasi.getReservasiTerbaruForAdmin().collect{
                reservasi.clear()
                reservasi.addAll(it)
            }
        }
    }

    fun getReservasi() = reservasi

    fun getPerangkat() = perangkat

    fun getPickedReservasi() = pickedReservasi.value

    fun setPickedReservasi(value: Reservasi?){
        pickedReservasi.value = value
    }

    fun ubahStatusReservasi(
        idReservasi:String,
        status:String,
        onSuccess:() -> Unit,
        onFailed:(String) -> Unit
    ){
        KontrolReservasi.updateStatusReservasi(
            idReservasi,
            status,
            onSuccess,
            onFailed
        )
    }

    init {
        loadReservasi()

        viewModelScope.launch {
            KontrolJadwal.getPerangkat().collect{
                perangkat.clear()
                perangkat.putAll(it.associate {
                    it.getIdPerangkat() to it.getNama()
                })
            }
        }
    }
}