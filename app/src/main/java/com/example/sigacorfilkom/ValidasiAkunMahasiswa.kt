package com.example.sigacorfilkom

import java.util.Date


class ValidasiAkunMahasiswa(
    idValidasi: String?,
    validator: Admin?,
    mahasiswa: Mahasiswa?,
    divalidasiPada: Date?
) {
    val idValidasi: String?
        get() {
            // TODO - implement ValidasiAkunMahasiswa.getIdValidasi
            throw UnsupportedOperationException()
        }
    val divalidasiPada: Date? = null

    /**
     *
     * @param idValidasi
     * @param validator
     * @param mahasiswa
     * @param divalidasiPada
     */
    init {
        // TODO - implement ValidasiAkunMahasiswa.ValidasiAkunMahasiswa
        throw UnsupportedOperationException()
    }

    fun getIdValidasi() {
        // TODO - implement ValidasiAkunMahasiswa.getIdValidasi
        throw java.lang.UnsupportedOperationException()
    }

    fun getDivalidasiPada(): Date? {
        return divalidasiPada
    }

}