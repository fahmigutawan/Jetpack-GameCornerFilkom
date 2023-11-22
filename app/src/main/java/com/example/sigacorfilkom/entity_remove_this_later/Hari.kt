package com.example.sigacorfilkom.entity_remove_this_later

class Hari {
    private var tanggal: Int
    private var bulan: Int
    private var tahun: Int
    private var isDitutup: Boolean = true
    private var alasanDitutup: String = ""

    constructor(tanggal: Int, bulan: Int, tahun: Int, isDitutup: Boolean, alasanDitutup: String) {
        this.tahun = tahun
        this.tanggal = tanggal
        this.bulan = bulan
        this.isDitutup = isDitutup
        this.alasanDitutup = alasanDitutup
    }

    fun getIsDitutup() = isDitutup

    fun getAlasanDitutup() = alasanDitutup

    fun getTanggal() = tanggal

    fun getBulan() = bulan

    fun getTahun() = tahun
}
