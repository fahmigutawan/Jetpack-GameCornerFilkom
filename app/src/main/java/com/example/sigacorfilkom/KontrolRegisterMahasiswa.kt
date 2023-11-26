package com.example.sigacorfilkom

import androidx.navigation.NavController


class KontrolRegisterMahasiswa(navigasi: NavController) {
    private val navigasi: NavController

    init {
        this.navigasi = navigasi
    }

    fun tampilkanRegisterMahasiswa() {
        /**
         *  CALL   tampilkan
         *  TUJUAN (B) HalamanRegisterMahasiswa
         */
        navigasi.navigate("register_mahasiswa")
    }

    suspend fun registerMahasiswa(
        nim: String,
        nama: String,
        password: String
    ) {
        /**
         *  CALL   <<create>>
         *  TUJUAN (E) Mahasiswa
         */
        val mahasiswa = Mahasiswa(
            nim = nim,
            nama = nama,
            password = password
        )

        /**
         *  CALL   validateNimIsNumber()
         *  TUJUAN (E) Mahasiswa
         *  RETURN boolean
         */
        var hasilValidasi = mahasiswa.validateNimIsNumber()
        /**
         *  OPT false
         */
        if (!hasilValidasi) {
            throw Exception("NIM Hanya boleh angka")
        }

        /**
         *  CALL   validatePanjangNim()
         *  TUJUAN (E) Mahasiswa
         *  RETURN boolean
         */
        hasilValidasi = mahasiswa.validatePanjangNim()
        /**
         *  OPT false
         */
        if (!hasilValidasi) {
            throw Exception("Masukkan NIM yang benar")
        }

        /**
         *  CALL   validateNimFilkom()
         *  TUJUAN (E) Mahasiswa
         *  RETURN boolean
         */
        hasilValidasi = mahasiswa.validateNimFilkom()
        /**
         *  OPT false
         */
        if (!hasilValidasi) {
            throw Exception("Hanya mahasiswa FILKOM yang bisa mendaftar")
        }

        /**
         *  CALL   <<create>>
         *  TUJUAN (E) DaftarAkunMahasiswa
         *  RETURN boolean
         */
        val daftarAkunMahasiswa = DaftarAkunMahasiswa()
        /**
         *  CALL   buatAkunMahasiswa(mahasiswa)
         *  TUJUAN (E) DaftarAkunMahasiswa
         */
        daftarAkunMahasiswa.buatAkunMahasiswa(mahasiswa)

        /**
         *  CALL   tampilkan
         *  TUJUAN (B) HalamanUtamaMahasiswa
         */
        navigasi.navigate("home_mahasiswa")
    }
}