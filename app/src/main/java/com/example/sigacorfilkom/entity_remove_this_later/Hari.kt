package com.example.sigacorfilkom.entity_remove_this_later

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.Calendar

class Hari {
    private var tanggal: Int
    private var bulan: Int
    private var tahun: Int
    private var isDitutup: Flow<Boolean>
    private var alasanDitutup: Flow<Boolean>

    constructor(tanggal: Int, bulan: Int, tahun: Int) {
        this.tahun = tahun
        this.tanggal = tanggal
        this.bulan = bulan

        isDitutup = callbackFlow {
            //TODO Get status ditutup dari DB
            awaitClose()
        }

        alasanDitutup = callbackFlow {
            //TODO Get alasan ditutup dari DB
            awaitClose()
        }
    }

    fun ubahStatusTutup(alasan:String) = callbackFlow<Boolean> {
        //TODO Update status tutup
        awaitClose()
    }

    fun getIsDitutup() = isDitutup

    fun getAlasanDitutup() = alasanDitutup

    fun getTanggal() = tanggal

    fun getBulan() = bulan

    fun getTahun() = tahun

    fun getTimeInMillis(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(tahun, bulan, tanggal)
        return calendar.timeInMillis
    }

    fun getCalendar():Calendar {
        val tmp = Calendar.getInstance()
        tmp.set(tahun, bulan, tanggal)
        return tmp
    }
}
