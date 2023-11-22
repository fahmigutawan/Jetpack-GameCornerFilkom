package com.example.sigacorfilkom.boundary_remove_this_later.login

import androidx.lifecycle.ViewModel
import com.example.sigacorfilkom.boundary_remove_this_later.login_mahasiswa.KontrolLoginMahasiswa

class HalamanLogin(
    private val kontrolLoginMahasiswa: KontrolLoginMahasiswa
) : ViewModel() {
    fun loginMahasiswa() {
        kontrolLoginMahasiswa.tampilkanHalamanLoginMahasiswa()
    }
}