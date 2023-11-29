package com.example.sigacorfilkom.model

class Admin {
    private var nip:String = ""
    private var password:String = ""

    constructor(
        nip:String,
        password:String
    ){
        this.nip = nip
        this.password = password
    }

    constructor()

    fun getNip() = nip

    fun setNip(value:String){
        nip = value
    }

    fun setPassword(value:String) {
        password = value
    }

    fun validateNipIsNumber(){
        if(!nip.matches(Regex("\\d+"))){
            throw Exception("NIP Hanya boleh angka")
        }
    }

    fun validatePassword(correctPassword:String):Boolean = this.password == correctPassword
}