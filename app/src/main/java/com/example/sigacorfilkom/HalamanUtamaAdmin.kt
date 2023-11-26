package com.example.sigacorfilkom

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HalamanUtamaAdmin(
    kontrolJadwal: KontrolJadwal,
    kontrolReservasi: KontrolReservasi,
    kontrolLogout: KontrolLogout
) : ViewModel() {
    private val reservasi = mutableStateListOf<Reservasi>()
    private val perangkat = mutableMapOf<String, String>()
    private val pickedReservasi = mutableStateOf<Reservasi?>(null)
    private val kontrolJadwal: KontrolJadwal
    private val kontrolReservasi: KontrolReservasi
    private val kontrolLogout: KontrolLogout

    init {
        this.kontrolReservasi = kontrolReservasi
        this.kontrolJadwal = kontrolJadwal
        this.kontrolLogout = kontrolLogout
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

    /**
     *  EVENT logout
     */
    fun logout() {
        /**
         *  CALL   logoutAdmin()
         *  TUJUAN (C) KontrolLogout
         */
        kontrolLogout.logoutAdmin()
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