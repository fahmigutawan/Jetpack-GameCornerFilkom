package com.example.sigacorfilkom.entity_remove_this_later

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

    fun getNip() = nip

    fun setNip(value:String){
        nip = value
    }

    fun setPassword(value:String) {
        password = value
    }

    fun validatePassword(correctPassword:String):Boolean = this.password == correctPassword
}