package com.example.sigacorfilkom

import java.util.Date


class KontrolJadwal {
    val daftarJadwalTerpesanAtauDitutup: Array<Jadwal> = arrayOf()

    /**
     *
     * @param tanggal
     */
    fun tutupJadwal(tanggal: Date?) {}

    fun getDaftarJadwalTerpesanAtauDitutup(): Array<Jadwal> {
        throw UnsupportedOperationException()
    }
}