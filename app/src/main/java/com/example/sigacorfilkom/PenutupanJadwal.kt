package com.example.sigacorfilkom


class PenutupanJadwal(penutup: Admin?, jadwal: Array<Jadwal?>?) {
    /**
     *
     * @param alasan
     */
    var alasan: String? = null

    /**
     *
     * @param penutup
     * @param jadwal
     */
    init {
        // TODO - implement PenutupanJadwal.PenutupanJadwal
        throw UnsupportedOperationException()
    }

    fun getAlasan(): String? {
        return alasan
    }

    /**
     *
     * @param alasan
     */
    fun setAlasan(alasan: String?) {
        this.alasan = alasan
    }
}