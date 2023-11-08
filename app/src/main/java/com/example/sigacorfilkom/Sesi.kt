package com.example.sigacorfilkom


class Sesi {
    /**
     *
     * @param akun
     */
    var akun: Akun? = null

    fun getAkun(): Akun? {
        return akun
    }

    /**
     *
     * @param akun
     */
    fun setAkun(akun: Akun?) {
        this.akun = akun
    }

    fun validasiAkunMahasiswa() {
        // TODO - implement Sesi.validasiAkunMahasiswa
        throw UnsupportedOperationException()
    }

    fun validasiAkunAdmin() {
        // TODO - implement Sesi.validasiAkunAdmin
        throw UnsupportedOperationException()
    }
}