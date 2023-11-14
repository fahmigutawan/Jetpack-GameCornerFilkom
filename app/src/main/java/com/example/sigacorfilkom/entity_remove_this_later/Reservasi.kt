package com.example.sigacorfilkom.entity_remove_this_later

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar
import kotlin.math.tan

class Reservasi {
    private var reservasiId:String
    private var nimPeminjam:String
    private var status:String
    private var nomorSesi:Int
    private var idPerangkat:String
    private var tanggal:Calendar
    private val defaultStatus = "Menunggu"

    constructor(
        reservasiId:String,
        nimPeminjam:String,
        status:String = "Menunggu",
        nomorSesi:Int,
        idPerangkat:String,
        tanggal:Int,
        bulan:Int,
        tahun:Int
    ){
        this.reservasiId = reservasiId
        this.nimPeminjam = nimPeminjam
        this.status = status
        this.nomorSesi = nomorSesi
        this.idPerangkat = idPerangkat
        this.tanggal = Calendar.getInstance().apply {
            set(tahun, bulan - 1, tanggal)
        }
    }

    fun getReservasiId() = reservasiId

    fun getNimPeminjam() = nimPeminjam

    fun getStatus() = status

    fun getNomorSesi() = nomorSesi

    fun getIdPerangkat() = idPerangkat

    fun getLocalDate(): LocalDate{
        val instant = Instant.ofEpochMilli(
            tanggal.timeInMillis
        )

        val date = instant.atZone(ZoneId.systemDefault()).toLocalDate()
        return date
    }

    fun getDefaultStatus() = defaultStatus
}