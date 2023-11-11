package com.example.sigacorfilkom.entity_remove_this_later

import android.util.Log
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar

class Hari {
    private var tanggal: Int
    private var bulan: Int
    private var tahun: Int
    private var isDitutup: Boolean = true
    private var alasanDitutup: String = ""

    constructor(tanggal: Int, bulan: Int, tahun: Int) {
        this.tahun = tahun
        this.tanggal = tanggal
        this.bulan = bulan
    }

    fun getIsDitutup() = isDitutup

    fun getAlasanDitutup() = alasanDitutup

    fun getFormattedDate():String {
        val calendar = Calendar.getInstance()
        calendar.set(tahun, bulan - 1, tanggal, 0,0,0)

        val instant = Instant.ofEpochMilli(calendar.timeInMillis)
        val localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate()

        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        return localDate.format(formatter)
    }

    fun getTanggal() = tanggal

    fun getBulan() = bulan

    fun getTahun() = tahun

    fun getTimeInMillis(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(tahun, bulan - 1, tanggal, 0,0,0)
        return calendar.timeInMillis
    }
}
