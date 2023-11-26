package com.example.sigacorfilkom.entity_remove_this_later

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class Mahasiswa {
    private var nim:String = ""
    private var password:String = ""
    private var nama:String = ""

    constructor(
        nim:String,
        password:String,
        nama:String
    ){
        this.nim = nim
        this.password = password
        this.nama = nama
    }

    constructor(
        nim:String,
        password:String
    ){
        this.nim = nim
        this.password = password
    }

    fun getNim() = nim

    fun getNama() = nama

    fun setNama(value:String){
        this.nama = value
    }

    fun validateNimIs15Digit() = nim.length == 15

    fun validateNimIsFilkom() = nim.substring(2, 5) == "515"

    fun validateNimIsNumber() = nim.matches(Regex("\\d+"))

    fun validatePassword(correctPassword:String):Boolean{
        return this.password == correctPassword
    }
}