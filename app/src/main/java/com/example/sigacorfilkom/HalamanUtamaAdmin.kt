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

    /**
     *  EVENT Lihat List Reservasi
     */
    fun lihatListReservasi() {
        viewModelScope.launch {
           /**
            *  CALL   tampilkanHalamanRiwayatReservasiForAdmin()
            *  TUJUAN (C) KontrolReservasi
            */
            kontrolReservasi.tampilkanHalamanRiwayatReservasiForAdmin().collect {
                reservasi.clear()
                reservasi.addAll(it)
            }
        }
      /**
        *  CALL   tampilkan daftar reservasi
        *  TUJUAN (B) HalamanUtamaAdmin
        */
        navigasi.navigate("halaman_utama_admin")
    }

    fun getReservasi() = reservasi

    fun getPerangkat() = perangkat

    fun getPickedReservasi() = pickedReservasi.value

    fun setPickedReservasi(value: Reservasi?) {
        pickedReservasi.value = value
    }

    fun setDaftarReservasi(daftarReservasi: List<Reservasi>) {
        this.daftarReservasi.clear()
        this.daftarReservasi.addAll(daftarReservasi)
    }

    /**
     *  EVENT submitBenar | submitSalah
     */
    fun validasiReservasi(
        reservasi: Reservasi,
        isValid: Boolean,
        onSuccess: () -> Unit,
        onFailed: (String) -> Unit
    ) {
        viewModelScope.launch {
            /**
             *  CALL   validasiReservasi()
             *  TUJUAN (C) KontrolReservasi
             */
            kontrolReservasi.validasiReservasi(
                reservasi,
                isValid,
                onSuccess,
                onFailed
            )
        }
    }

    /**
     *  EVENT tutup jadwal
     */
     fun tutupJadwal() {
        /**
        *  CALL tampilkanHalamanTutupJadwal
        *  TUJUAN (C) KontrolJadwal
        */
        kontrolJadwal.tampilkanHalamanTutupJadwal()
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
        lihatListReservasi()

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