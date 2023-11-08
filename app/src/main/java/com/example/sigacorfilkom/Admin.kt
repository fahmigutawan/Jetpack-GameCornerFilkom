package com.example.sigacorfilkom


class Admin(nip: String, nama: String, password: String, email: String) :
    Akun() {

    private val nip: String
    private val nama: String
    private val password: String
    private val email: String

    /**
     *
     * @param nip
     * @param nama
     * @param password
     * @param email
     */
    init {
        this.nip = nip
        this.nama = nama
        this.password = password
        this.email = email
    }

    fun getNip(): String? {
        return nip
    }

    fun getNama(): String? {
        return nama
    }

    fun getPassword(): String? {
        return password
    }

    fun getEmail(): String? {
        return email
    }

    /**
     *
     * @param password
     */
    fun validasiPassword(password: String?): Boolean {
        // TODO implement
        throw UnsupportedOperationException()
    }
}