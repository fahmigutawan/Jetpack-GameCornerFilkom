package com.example.sigacorfilkom

import org.junit.Test

class MahasiswaTest {
    @Test
    fun testValidateNimIsNumberDenganNimNumber() {
        val mahasiswa = Mahasiswa("215150201111001", "", "Tes Mahasiswa")
        val hasilValidasi = mahasiswa.validateNimIsNumber()
        assert(hasilValidasi)
    }

    @Test
    fun testValidateNimIsNumberDenganNimBukanNumber() {
        val mahasiswa = Mahasiswa("nim-bukan-number", "", "Tes Mahasiswa")
        val hasilValidasi = mahasiswa.validateNimIsNumber()
        assert(!hasilValidasi)
    }

    @Test
    fun testValidatePanjangNimDenganPanjang15() {
        val mahasiswa = Mahasiswa("215150201111001", "", "Tes Mahasiswa")
        val hasilValidasi = mahasiswa.validatePanjangNim()
        assert(hasilValidasi)
    }

    @Test
    fun testValidatePanjangNimDenganPanjangLebihDari15() {
        val mahasiswa = Mahasiswa("2151502011110011", "", "Tes Mahasiswa")
        val hasilValidasi = mahasiswa.validatePanjangNim()
        assert(!hasilValidasi)
    }

    @Test
    fun testValidatePanjangNimDenganPanjangKurangDari15() {
        val mahasiswa = Mahasiswa("21515020111100", "", "Tes Mahasiswa")
        val hasilValidasi = mahasiswa.validatePanjangNim()
        assert(!hasilValidasi)
    }

    @Test
    fun testValidateNimFilkomDenganKodeFakultasFilkom() {
        val mahasiswa = Mahasiswa("215150201111001", "", "Tes Mahasiswa")
        val hasilValidasi = mahasiswa.validateNimFilkom()
        assert(hasilValidasi)
    }

    @Test
    fun testValidateNimFilkomDenganKodeFakultasBukanFilkom() {
        val mahasiswa = Mahasiswa("215200201111001", "", "Tes Mahasiswa")
        val hasilValidasi = mahasiswa.validateNimFilkom()
        assert(!hasilValidasi)
    }
}