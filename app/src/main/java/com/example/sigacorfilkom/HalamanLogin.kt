package com.example.sigacorfilkom

import androidx.lifecycle.ViewModel
import com.example.sigacorfilkom.KontrolLoginMahasiswa

class HalamanLogin(
    kontrolLoginMahasiswa: KontrolLoginMahasiswa,
    kontrolLoginAdmin: KontrolLoginAdmin
) : ViewModel() {
    private val kontrolLoginMahasiswa: KontrolLoginMahasiswa
    private val kontrolLoginAdmin: KontrolLoginAdmin

    init {
        this.kontrolLoginMahasiswa = kontrolLoginMahasiswa
        this.kontrolLoginAdmin = kontrolLoginAdmin
    }

    fun loginMahasiswa() {
        kontrolLoginMahasiswa.tampilkanHalamanLoginMahasiswa()
    }

    fun loginAdmin() {
        kontrolLoginAdmin.tampilkanHalamanLoginAdmin()
    }
}