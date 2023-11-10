package com.example.sigacorfilkom.entity_remove_this_later

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class Mahasiswa {
    private var nim:String = ""
    private var password:String = ""
    private var nama:String = ""

    fun getNim() = nim

    fun getNama() = nama

    fun authenticate(nim:String, password:String) = callbackFlow<Boolean> {
        //TODO Check username and password in DB
        awaitClose()
    }

    fun saveDataMahasiswa(nim:String, password:String, name:String) = callbackFlow<Boolean> {
        //TODO Add new mahasiswa at DB
        awaitClose()
    }
}