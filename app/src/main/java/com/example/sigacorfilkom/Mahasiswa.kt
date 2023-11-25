package com.example.sigacorfilkom

class Mahasiswa {
    private var nim: String = ""
    private var password: String = ""
    private var nama: String = ""

    constructor(
        nim: String,
        password: String,
        nama: String
    ) {
        this.nim = nim
        this.password = password
        this.nama = nama
    }

    constructor(
        nim: String,
        password: String
    ) {
        this.nim = nim
        this.password = password
    }

    fun getNim() = nim

    fun getNama() = nama

    fun setNama(value: String) {
        this.nama = value
    }

    fun validateNimFilkom(): Boolean {
        return (nim.substring(2, 5) == "515")
    }

    fun validateNimIsNumber(): Boolean {
        return nim.matches(Regex("\\d+"))
    }

    fun validatePanjangNim(): Boolean {
        return (nim.length == 15)
    }

    fun validatePassword(correctPassword: String): Boolean {
        return this.password == correctPassword
    }
}