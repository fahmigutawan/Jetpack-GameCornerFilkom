package com.example.sigacorfilkom.kontrol_remove_this_later

import androidx.navigation.NavController
import com.example.sigacorfilkom.DaftarAkunMahasiswa

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
            /**
             *  CALL   <<create>>
             *  TUJUAN (E) DaftarAkunMahasiswa
             */
            val daftarAkunMahasiswa = DaftarAkunMahasiswa()

            /**
             *  CALL   validasiAkunMahasiswa
             *  TUJUAN (E) DaftarAkunMahasiswa
             *  RETURN boolean
             */
            val hasilValidasi =  daftarAkunMahasiswa.validasiAkunMahasiswa(nim, password)

            /**
             *  ALT true
             */
            if(hasilValidasi) {
                /**
                 *  CALL   tampilkan
                 *  TUJUAN (B) HalamanUtamaMahasiswa
                 */
                navigasi.navigate("home_mahasiswa")
            }
            /**
             *  ALT false
             */
            else {
                /**
                 *  CALL tampilkanErrorTidakValid
                 */
                onFailed("NIM atau Password Salah atau tidak terdaftar")
            }
        } catch (e: Exception) {
            onFailed(e.message.toString())
        }
    }
}