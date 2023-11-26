package com.example.sigacorfilkom

class Otentikasi {
    companion object {
        private var mahasiswa: Mahasiswa? = null
        private var admin: Admin? = null

        fun getMahasiswa(): Mahasiswa? {
            return mahasiswa
        }

        fun setMahasiswa(mahasiswa: Mahasiswa) {
            Companion.mahasiswa = mahasiswa
        }

        fun clearMahasiswa() {
            mahasiswa = null
        }

        fun getAdmin(): Admin? {
            return admin
        }

        fun setAdmin(admin: Admin) {
            Companion.admin = admin
        }

        fun clearAdmin() {
            admin = null
        }
    }
}