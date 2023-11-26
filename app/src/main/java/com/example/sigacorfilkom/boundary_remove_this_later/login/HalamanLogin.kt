package com.example.sigacorfilkom.boundary_remove_this_later.login

import androidx.lifecycle.ViewModel
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolNavigasi

class HalamanLogin(
    kontrolNavigasi: KontrolNavigasi
) : ViewModel() {
    private val kontrolNavigasi:KontrolNavigasi

    init {
        this.kontrolNavigasi = kontrolNavigasi
    }
    fun navigasikeLoginMahasiswa() = kontrolNavigasi.navigasiKeLoginMahasiswa()

    fun navigasiKeLoginAdmin() = kontrolNavigasi.navigasiKeLoginAdmin()
}