package com.example.sigacorfilkom


class Perangkat(idPerangkat: String?, nama: String?, jenis: String?) {
    val idPerangkat: String? = null
    val nama: String? = null
    val jenis: String? = null

    /**
     *
     * @param idPerangkat
     * @param nama
     * @param jenis
     */
    init {
        // TODO - implement Perangkat.Perangkat
        throw UnsupportedOperationException()
    }

    fun getIdPerangkat(): String? {
        return idPerangkat
    }

    fun getNama(): String? {
        return nama
    }

    fun getJenis(): String? {
        return jenis
    }
}