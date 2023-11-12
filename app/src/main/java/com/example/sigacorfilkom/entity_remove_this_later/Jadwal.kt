package com.example.sigacorfilkom.entity_remove_this_later

import java.time.LocalDate

class Jadwal {
    private var hari: List<Hari>
    private var perangkat: List<Perangkat> = listOf()
    private var sesi: List<Sesi> = listOf()

    constructor() {
        hari = listOf(
            Hari(
                tanggal = LocalDate.now().dayOfMonth,
                bulan = LocalDate.now().month.value,
                tahun = LocalDate.now().year
            ),
            Hari(
                tanggal = LocalDate.now().plusDays(1).dayOfMonth,
                bulan = LocalDate.now().plusDays(1).month.value,
                tahun = LocalDate.now().plusDays(1).year
            ),
            Hari(
                tanggal = LocalDate.now().plusDays(2).dayOfMonth,
                bulan = LocalDate.now().plusDays(2).month.value,
                tahun = LocalDate.now().plusDays(2).year
            ),
        )
    }

    fun getHari() = hari

    fun getPerangkat() = perangkat

    fun getPerangkatById(idPerangkat: String): Perangkat? = perangkat.findLast {
        it.getIdPerangkat() == idPerangkat
    }

    fun setPerangkat(value: List<Perangkat>) {
        perangkat = value
    }

    fun getSesi() = sesi

    fun setSesi(value: List<Sesi>) {
        sesi = value
    }
}