package com.example.sigacorfilkom

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import kotlin.math.tan

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

        val cal = Calendar.getInstance()
        cal.set(tahun, bulan - 1, tanggal)
        val instant = Instant.ofEpochMilli(cal.timeInMillis)
        val localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate()

        //Sabtu minggu
        when(localDate.dayOfWeek.value){
            6,7 -> {
                isDitutup = true
                alasanDitutup = "Hari Sabtu dan Minggu Tutup"
            }

            else -> {
                FirebaseFirestore
                    .getInstance()
                    .collection("jadwal_tutup")
                    .document("${localDate.dayOfMonth}:${localDate.monthValue}:${localDate.year}")
                    .get()
                    .addOnSuccessListener {
                        if(it.data == null){
                            isDitutup = false
                        }else{
                            isDitutup = true
                            alasanDitutup = it["alasan"] as String
                        }
                    }
            }
        }
    }

    fun getIsDitutup() = isDitutup

    fun getAlasanDitutup() = alasanDitutup

    fun getTanggal() = tanggal

    fun getBulan() = bulan

    fun getTahun() = tahun

    fun reloadHari(){
        val cal = Calendar.getInstance()
        cal.set(tahun, bulan - 1, tanggal)
        val instant = Instant.ofEpochMilli(cal.timeInMillis)
        val localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate()

        //Sabtu minggu
        when(localDate.dayOfWeek.value) {
            6, 7 -> {
                isDitutup = true
                alasanDitutup = "Hari Sabtu dan Minggu Tutup"
            }

            else -> {
                FirebaseFirestore
                    .getInstance()
                    .collection("jadwal_tutup")
                    .document("${localDate.dayOfMonth}:${localDate.monthValue}:${localDate.year}")
                    .get()
                    .addOnSuccessListener {
                        if (it.data == null) {
                            isDitutup = false
                        } else {
                            isDitutup = true
                            alasanDitutup = it["alasan"] as String
                        }
                    }
            }
        }
    }
}
