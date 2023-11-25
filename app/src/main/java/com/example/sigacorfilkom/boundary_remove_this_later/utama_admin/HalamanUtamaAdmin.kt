package com.example.sigacorfilkom.boundary_remove_this_later.utama_admin

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sigacorfilkom.entity_remove_this_later.Reservasi
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolJadwal
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolNavigasi
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolOtentikasi
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolReservasi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HalamanUtamaAdmin(
    kontrolJadwal: KontrolJadwal,
    kontrolReservasi: KontrolReservasi,
    kontrolOtentikasi: KontrolOtentikasi,
    kontrolNavigasi: KontrolNavigasi
) : ViewModel() {
    private val reservasi = mutableStateListOf<Reservasi>()
    private val perangkat = mutableMapOf<String, String>()
    private val pickedReservasi = mutableStateOf<Reservasi?>(null)
    private val kontrolJadwal: KontrolJadwal
    private val kontrolReservasi: KontrolReservasi
    private val kontrolOtentikasi: KontrolOtentikasi
    private val kontrolNavigasi:KontrolNavigasi

    init {
        this.kontrolReservasi = kontrolReservasi
        this.kontrolJadwal = kontrolJadwal
        this.kontrolOtentikasi = kontrolOtentikasi
        this.kontrolNavigasi = kontrolNavigasi
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
        kontrolOtentikasi.logout()
    }

    init {
        loadReservasi()

        viewModelScope.launch {
            kontrolJadwal.getPerangkat().collect {
                perangkat.clear()
                perangkat.putAll(it.associate {
                    it.getIdPerangkat() to it.getNama()
                })
            }
        }
    }

    fun navigasiKeHalamanLogin() = kontrolNavigasi.navigasiKeHalamanLogin()
}