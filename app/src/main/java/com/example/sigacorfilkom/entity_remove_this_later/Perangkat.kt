package com.example.sigacorfilkom.entity_remove_this_later

class Perangkat {
    private var idPerangkat:String
    private var nama:String

    constructor(
        idPerangkat:String,
        nama:String
    ){
        this.idPerangkat = idPerangkat
        this.nama = nama
    }

    fun getIdPerangkat() = idPerangkat
    fun getNama() = nama
}