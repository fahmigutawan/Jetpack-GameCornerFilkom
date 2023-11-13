package com.example.sigacorfilkom.entity_remove_this_later

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class Mahasiswa {
    private var nim:String = ""
    private var password:String = ""
    private var nama:String = ""

    fun getNim() = nim

    fun getNama() = nama

    fun setNim(value:String){
        nim = value
    }

    fun setPassword(value:String){
        password = value
    }

    fun setNama(value:String){
        nama = value
    }
}