package com.example.sigacorfilkom.boundary_remove_this_later.otentikasi

import com.example.sigacorfilkom.entity_remove_this_later.Mahasiswa

class Otentikasi {
    companion object {
        private var mahasiswa: Mahasiswa? = null

        fun getMahasiswa(): Mahasiswa? {
            return mahasiswa
        }

        fun setMahasiswa(mahasiswa: Mahasiswa?) {
            this.mahasiswa = mahasiswa
        }
    }
}