package com.example.sigacorfilkom.kontrol_remove_this_later

import androidx.navigation.NavController
import com.example.sigacorfilkom.Otentikasi

class KontrolUtamaMahasiswa(navigasi: NavController) {
    private val navigasi: NavController

    init {
        this.navigasi = navigasi
    }

    fun logout() {
        /**
         *  CALL   clearMahasiswa
         *  TUJUAN (E) Otentikasi
         */
        Otentikasi.clearMahasiswa()

        /**
         *  CALL   tampilkan
         *  TUJUAN (B) HalamanLogin
         */
        navigasi.navigate("login")
    }
}