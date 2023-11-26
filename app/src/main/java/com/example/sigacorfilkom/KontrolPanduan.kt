package com.example.sigacorfilkom

import androidx.navigation.NavController

class KontrolPanduan(navigasi: NavController) {
    private val navigasi: NavController

    init {
        this.navigasi = navigasi
    }

    fun tampilkanHalamanPanduan() {
        /**
         *  CALL   tampilkan
         *  TUJUAN (B) HalamanPanduan
         */
        navigasi.navigate("panduan_mahasiswa")
    }
}