package com.example.sigacorfilkom.entity

data class Admin(
    val nip: String,
    val nama: String,
    val username: String,
    val hashPassword: String,
    val email: String,
    val alamat: String
)