package com.example.sigacorfilkom

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sigacorfilkom.KontrolReservasi
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolUtamaAdmin
import kotlinx.coroutines.launch

class HalamanUtamaAdmin(
    kontrolJadwal: KontrolJadwal,
    kontrolReservasi: KontrolReservasi,
    kontrolUtamaAdmin: KontrolUtamaAdmin
) : ViewModel() {
    private val reservasi = mutableStateListOf<Reservasi>()
    private val perangkat = mutableMapOf<String, String>()
    private val pickedReservasi = mutableStateOf<Reservasi?>(null)
    private val kontrolJadwal: KontrolJadwal
    private val kontrolReservasi: KontrolReservasi
    private val kontrolUtamaAdmin: KontrolUtamaAdmin

    init {
        this.kontrolReservasi = kontrolReservasi
        this.kontrolJadwal = kontrolJadwal
        this.kontrolUtamaAdmin = kontrolUtamaAdmin
    }

    fun loadReservasi() {
        viewModelScope.launch {
            kontrolReservasi.getReservasiTerbaruForAdmin().collect {
                reservasi.clear()
                reservasi.addAll(it)
            }
        }
    }

    fun getReservasi() = reservasi

    fun getPerangkat() = perangkat

    fun getPickedReservasi() = pickedReservasi.value

    fun setPickedReservasi(value: Reservasi?) {
        pickedReservasi.value = value
    }

    fun ubahStatusReservasi(
        idReservasi: String,
        status: String,
        onSuccess: () -> Unit,
        onFailed: (String) -> Unit
    ) {
        kontrolReservasi.updateStatusReservasi(
            idReservasi,
            status,
            onSuccess,
            onFailed
        )
    }

    fun logout() {
        /**
         *  CALL   logout()
         *  TUJUAN (C) KontrolUtamaAdmin
         */
        kontrolUtamaAdmin.logout()
    }

    init {
        loadReservasi()

        viewModelScope.launch {
//            kontrolJadwal.getDaftarPerangkat().collect {
//                perangkat.clear()
//                perangkat.putAll(it.associate {
//                    it.getIdPerangkat() to it.getNama()
//                })
//            }
        }
    }
}