package com.example.sigacorfilkom.entity_remove_this_later

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class Sesi {
    private var idPerangkat: String = ""
    private var nomorSesi: Int
    private var waktu: String
    private var booked: Boolean = true

    fun checkBookedKarenaJam(timeMillis:Long) {
        if(LocalTime.now().isAfter(getStartTime()) && timeMillis <= System.currentTimeMillis()){
            booked = true
        }
    }

    constructor(
        idPerangkat: String,
        nomorSesi: Int,
        waktu: String
    ) {
        this.idPerangkat = idPerangkat
        this.nomorSesi = nomorSesi
        this.waktu = waktu
    }

    constructor(
        nomorSesi: Int,
        waktu: String
    ) {
        this.nomorSesi = nomorSesi
        this.waktu = waktu
    }

    fun setBooked(value: Boolean) {
        booked = value
    }

    fun getBooked() = booked

    fun setIdPerangkat(value: String) {
        idPerangkat = value
    }

    fun getIdPerangkat() = idPerangkat

    fun getWaktu() = waktu

    fun getSesiNumber() = nomorSesi

    fun getStartTime(): LocalTime? {
        val first = waktu.split(" - ")[0]
        val format = DateTimeFormatter.ofPattern("HH.mm")

        if (first.isNotEmpty()) {
            return LocalTime.parse(first, format)
        }

        return null
    }

    fun getEndTime(): LocalTime? {
        val first = waktu.split(" - ")[1]
        val format = DateTimeFormatter.ofPattern("HH.mm")

        if (first.isNotEmpty()) {
            return LocalTime.parse(first, format)
        }

        return null
    }
}