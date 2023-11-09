package com.example.sigacorfilkom

import java.util.Date


class HalamanTutupJadwal {
    /**
     *
     * @param tanggalDitutup
     */
    private var tanggalDitutup: Date? = null

    fun getTanggalDitutup(): Date? {
        return tanggalDitutup
    }

    /**
     *
     * @param tanggalDitutup
     */
    fun setTanggalDitutup(tanggalDitutup: Date?) {
        this.tanggalDitutup = tanggalDitutup
    }
}