package com.example.sigacorfilkom.presentation.utama_admin

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sigacorfilkom.model.Reservasi
import com.example.sigacorfilkom.repoJadwal
import com.example.sigacorfilkom.repoReservasi
import kotlinx.coroutines.launch

class UtamaAdminViewModel: ViewModel() {
    private val reservasi = mutableStateListOf<Reservasi>()
    private val perangkat = mutableMapOf<String, String>()
    private val pickedReservasi = mutableStateOf<Reservasi?>(null)

    fun loadReservasi(){
        viewModelScope.launch {
            repoReservasi.getReservasiTerbaruForAdmin().collect{
                reservasi.clear()
                reservasi.addAll(it)
            }
        }
    }

    fun getReservasi() = reservasi

    fun getPerangkat() = perangkat

    fun getPickedReservasi() = pickedReservasi.value

    fun setPickedReservasi(value:Reservasi?){
        pickedReservasi.value = value
    }

    fun ubahStatusReservasi(
        idReservasi:String,
        status:String,
        onSuccess:() -> Unit,
        onFailed:(String) -> Unit
    ){
        repoReservasi.updateStatusReservasi(
            idReservasi,
            status,
            onSuccess,
            onFailed
        )
    }

    init {
        loadReservasi()

        viewModelScope.launch {
            repoJadwal.getPerangkat().collect{
                perangkat.clear()
                perangkat.putAll(it.associate {
                    it.getIdPerangkat() to it.getNama()
                })
            }
        }
    }
}