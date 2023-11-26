package com.example.sigacorfilkom

import androidx.navigation.NavController

class KontrolLogout(navigasi: NavController) {
    private val navigasi: NavController

    init {
        this.navigasi = navigasi
    }

    fun logoutMahasiswa() {
        /**
         *  CALL   clearMahasiswa()
         *  TUJUAN (E) Otentikasi
         */
        Otentikasi.clearMahasiswa()

        /**
         *  CALL   tampilkan
         *  TUJUAN (B) HalamanLogin
         */
        navigasi.navigate("login")
    }

    fun logoutAdmin() {
        /**
         *  CALL   clearAdmin()
         *  TUJUAN (E) Otentikasi
         */
        Otentikasi.clearAdmin()

        /**
         *  CALL   tampilkan
         *  TUJUAN (B) HalamanLogin
         */
        navigasi.navigate("login")
    }
}