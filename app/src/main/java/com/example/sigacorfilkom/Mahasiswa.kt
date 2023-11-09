package com.example.sigacorfilkom


class Mahasiswa(
    nim: String,
    password: String,
    nama: String,
    fakultas: String,
    prodi: String
) :
    Akun() {
    private val nim: String
    private val password: String
    private val nama: String
    private val fakultas: String
    private val prodi: String

    /**
     *
     * @param tervalidasi
     */
    var tervalidasi = false

    /**
     *
     * @param nim
     * @param password
     * @param nama
     * @param fakultas
     * @param prodi
     */
    init {
        this.nim = nim
        this.password = password
        this.nama = nama
        this.fakultas = fakultas
        this.prodi = prodi
    }

    fun getNim(): String? {
        return nim
    }

    fun getPassword(): String? {
        return password
    }

    fun getNama(): String? {
        return nama
    }

    fun getFakultas(): String? {
        return fakultas
    }

    fun getProdi(): String? {
        return prodi
    }

    /**
     *
     * @param password
     */
    fun validasiPassword(password: String): Boolean {
        // TODO - implement Mahasiswa.validasiPassword
        throw UnsupportedOperationException()
    }

    fun getTervalidasi(): Boolean {
        return tervalidasi
    }

    /**
     *
     * @param tervalidasi
     */
    fun setTervalidasi(tervalidasi: Boolean) {
        this.tervalidasi = tervalidasi
    }
}