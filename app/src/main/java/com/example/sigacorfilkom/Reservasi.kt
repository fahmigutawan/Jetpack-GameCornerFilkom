package com.example.sigacorfilkom


class Reservasi(idReservasi: String, jadwal: Jadwal) {
    val idReservasi: String
    val jadwal: Jadwal

    /**
     *
     * @param idReservasi
     * @param jadwal
     */
    init {
        this.idReservasi = idReservasi
        this.jadwal = jadwal
    }

    fun getIdReservasi(): String? {
        return idReservasi
    }

    fun getDisetujui(): Boolean {
        // TODO - implement Reservasi.getDisetujui
        throw java.lang.UnsupportedOperationException()
    }

    /**
     *
     * @param disetujui
     */
    fun setDisetujui(disetujui: Boolean) {
        // TODO - implement Reservasi.setDisetujui
        throw java.lang.UnsupportedOperationException()
    }

    fun getJadwal(): Jadwal? {
        // TODO - implement Reservasi.getJadwal
        throw java.lang.UnsupportedOperationException()
    }
}