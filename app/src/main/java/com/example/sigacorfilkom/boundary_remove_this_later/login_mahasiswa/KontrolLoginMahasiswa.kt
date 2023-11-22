package com.example.sigacorfilkom.boundary_remove_this_later.login_mahasiswa

import androidx.navigation.NavController

class KontrolLoginMahasiswa(private val navigasi: NavController) {
    fun tampilkanHalamanLoginMahasiswa() {
        navigasi.navigate("login_mahasiswa")
    }
}