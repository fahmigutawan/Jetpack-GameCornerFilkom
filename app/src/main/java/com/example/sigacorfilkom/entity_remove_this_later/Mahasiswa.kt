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

    fun getNim() = nim

    fun getNama() = nama

    fun validateInputtedData(){
        if(nim.matches(Regex("\\d+"))){
            throw Exception("NIM Hanya boleh angka")
        }

        if(nim.length != 15){
            throw Exception("Masukkan NIM yang benar")
        }

        if(nim.substring(2, 5) != "515"){
            throw Exception(message = "Hanya mahasiswa FILKOM yang bisa mendaftar")
        }
    }

    fun validatePassword(password:String):Boolean{
        if(this.password != password){

        }

        return true
    }
}