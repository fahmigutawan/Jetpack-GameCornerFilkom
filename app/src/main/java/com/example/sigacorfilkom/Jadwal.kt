package com.example.sigacorfilkom

import java.util.Date


class Jadwal
/**
 *
 * @param tanggal
 * @param sesi
 * @param jamMulai
 * @param jamSelesai
 * @param tersedia
 * @param ditutup
 */
    (
    tanggal: Date,
    sesi: Int,
    jamMulai: Date,
    jamSelesai: Date,
    tersedia: Boolean,
    ditutup: Boolean
) {
    private val tanggal: Date
    private val sesi: Int
    private val jamMulai: Date
    private val jamSelesai: Date
    val tersedia = false
    val ditutup = false

    init {
        this.tanggal = tanggal
        this.sesi = sesi
        this.jamMulai = jamMulai
        this.jamSelesai = jamSelesai
    }

    fun getTanggal(): Date = tanggal

    fun getSesi(): Int {
        return sesi
    }

    fun getJamMulai(): Date = jamMulai

    fun getJamSelesai(): Date = jamSelesai

    fun getTersedia(): Boolean {
        return tersedia
    }

    /**
     *
     * @param tersedia
     */
    fun setTersedia(tersedia: Int) {
        // TODO - implement Jadwal.setTersedia
        throw UnsupportedOperationException()
    }

    /**
     *
     * @param ditutup
     */
    fun setDitutup(ditutup: Int) {
        // TODO - implement Jadwal.setDitutup
        throw UnsupportedOperationException()
    }
}