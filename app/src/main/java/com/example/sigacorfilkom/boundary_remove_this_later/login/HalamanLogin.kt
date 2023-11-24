package com.example.sigacorfilkom.boundary_remove_this_later.login

import androidx.lifecycle.ViewModel
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolLoginAdmin
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolLoginMahasiswa

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