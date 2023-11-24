package com.example.sigacorfilkom.kontrol_remove_this_later

import androidx.navigation.NavController
import com.example.sigacorfilkom.entity_remove_this_later.DaftarAkunMahasiswa

class KontrolLoginMahasiswa(navigasi: NavController) {
    private val navigasi: NavController

    init {
        this.navigasi = navigasi
    }

    fun tampilkanHalamanLoginMahasiswa() {
        navigasi.navigate("login_mahasiswa")
    }

    suspend fun login(
        nim: String,
        password: String,
        onFailed: (String) -> Unit
    ) {
        try {
            val daftarAkunMahasiswa = DaftarAkunMahasiswa()
            val hasilValidasi =  daftarAkunMahasiswa.validasiAkunMahasiswa(nim, password)
            if(hasilValidasi) {
                navigasi.navigate("home_mahasiswa")
            } else {
                onFailed("NIM atau Password Salah atau tidak terdaftar")
            }
        } catch (e: Exception) {
            onFailed(e.message.toString())
            return
        }
    }
}