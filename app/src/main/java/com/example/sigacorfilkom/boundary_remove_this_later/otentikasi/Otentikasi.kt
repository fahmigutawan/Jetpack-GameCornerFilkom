package com.example.sigacorfilkom.boundary_remove_this_later.otentikasi

import com.example.sigacorfilkom.entity_remove_this_later.Admin
import com.example.sigacorfilkom.Mahasiswa

class Otentikasi {
    companion object {
        private var mahasiswa: Mahasiswa? = null
        private var admin: Admin? = null

        fun getMahasiswa(): Mahasiswa? {
            return mahasiswa
        }

        fun setMahasiswa(mahasiswa: Mahasiswa) {
            this.mahasiswa = mahasiswa
        }

        fun clearMahasiswa() {
            this.mahasiswa = null
        }

        fun getAdmin(): Admin? {
            return admin
        }

        fun setAdmin(admin: Admin) {
            this.admin = admin
        }

        fun clearAdmin() {
            this.admin = null
        }
    }
}