package com.example.sigacorfilkom

import androidx.lifecycle.ViewModel


class HalamanRiwayatReservasi : ViewModel() {
    val riwayatReservasi: Array<Reservasi> = arrayOf()
    fun getRiwayatReservasi(): Array<Reservasi?>? {
        // TODO - implement ViewModelRiwayatReservasi.getRiwayatReservasi
        throw UnsupportedOperationException()
    }
}