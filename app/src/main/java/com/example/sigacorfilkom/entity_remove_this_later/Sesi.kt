package com.example.sigacorfilkom.entity_remove_this_later

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class Sesi {
    private var idPerangkat:String = ""
    private var nomorSesi:Int
    private var waktu:String
    private var booked: Boolean = true

    constructor(
        idPerangkat: String,
        nomorSesi:Int,
        waktu:String
    ){
        this.idPerangkat = idPerangkat
        this.nomorSesi = nomorSesi
        this.waktu = waktu
    }

    fun setBooked(value:Boolean) {
        booked = value
    }

    fun setIdPerangkat(value:String) {
        idPerangkat = value
    }

    fun getIdPerangkat() = idPerangkat

    fun getWaktu() = waktu

    fun getSesiNumber() = nomorSesi
}