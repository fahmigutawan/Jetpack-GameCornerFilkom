package com.example.sigacorfilkom


class KontrolOtentikasi {
    /**
     *
     * @param nim
     * @param password
     */
    fun loginMahasiswa(nim: String?, password: String?): Boolean = false

    /**
     *
     * @param nip
     * @param password
     */
    fun loginAdmin(nip: String?, password: String?): Boolean = false

    /**
     *
     * @param email
     * @param nama
     * @param nim
     * @param password
     */
    fun registerMahasiswa(email: String?, nama: String?, nim: String?, password: String?) {}
    fun logout() {}

    /**
     *
     * @param nim
     */
    fun validasiMahasiswa(nim: String?) {
        // TODO - implement KontrolAutentikasi.validasiMahasiswa
        throw UnsupportedOperationException()
    }
}