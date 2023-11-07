package com.example.sigacorfilkom.entity

data class Reservasi(
    val idReservasi: String,
    val jadwal: Jadwal,
    var disetujui: Boolean
)