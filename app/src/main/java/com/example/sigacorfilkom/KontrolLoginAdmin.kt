package com.example.sigacorfilkom

import androidx.navigation.NavController

class KontrolLoginAdmin(navigasi: NavController) {
    private val navigasi: NavController

    init {
        this.navigasi = navigasi
    }

    fun tampilkanHalamanLoginAdmin() {
        navigasi.navigate("login_admin")
    }

    suspend fun login(nip: String, password: String, onFailed: (String) -> Unit) {
        /**
         *  CALL   <<create>>
         *  TUJUAN (E) DaftarAkunAdmin
         */
        val daftarAkunAdmin = DaftarAkunAdmin()

        /**
         *  CALL   validasiAkunAdmin(nip, password)
         *  TUJUAN (E) DaftarAkunAdmin
         *  RETURN boolean
         */
        val hasilValidasi = daftarAkunAdmin.validasiAkunAdmin(nip, password)

        /**
         *  OPT true
         */
        if(hasilValidasi) {
            /**
             *  CALL   tampilkan
             *  TUJUAN (B) HalamanUtamaAdmin
             */
            navigasi.navigate("home_admin")
        }
        /**
         *  OPT false
         */
        else {
            /**
             *  CALL   tampilkan error tidak valid
             *  TUJUAN (B) HalamanLoginAdmin
             */
            onFailed("NIM atau Password Salah atau tidak terdaftar")
        }
    }
}