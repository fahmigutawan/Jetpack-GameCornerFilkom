package com.example.sigacorfilkom.entity

import java.util.Date

data class Jadwal(
    val tanggal: Date,
    val sesi: Int,
    val jamMulai: Date,
    val jamSelesai: Date,
    var tersedia: Boolean = false,
    var ditutup: Boolean = false
)