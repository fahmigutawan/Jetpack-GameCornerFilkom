package com.example.sigacorfilkom.entity_remove_this_later

import java.util.Calendar
import kotlin.math.tan

class Reservasi {
    private var reservasiId:String
    private var nimPeminjam:String
    private var status:String
    private var nomorSesi:String
    private var idPerangkat:String
    private var tanggal:Calendar

    constructor(
        reservasiId:String,
        nimPeminjam:String,
        status:String,
        nomorSesi:String,
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
            set(tahun, bulan, tanggal)
        }
    }

    fun getReservasiId() = reservasiId

    fun getNimPeminjam() = nimPeminjam

    fun getStatus() = status

    fun getNomorSesi() = nomorSesi

    fun getIdPerangkat() = idPerangkat

    fun getTanggal() = tanggal
}