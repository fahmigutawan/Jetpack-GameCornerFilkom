package com.example.sigacorfilkom.model

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

    fun validateInputtedData(){
        if(nim.length != 15){
            throw Exception("Masukkan NIM yang benar")
        }

        if(nim.substring(2, 5) != "515"){
            throw Exception("Hanya mahasiswa FILKOM yang bisa mendaftar")
        }
    }

    fun validateNimIsNumber(){
        if(!nim.matches(Regex("\\d+"))){
            throw Exception("NIM Hanya boleh angka")
        }
    }

    fun validatePassword(correctPassword:String):Boolean{
        return this.password == correctPassword
    }
}