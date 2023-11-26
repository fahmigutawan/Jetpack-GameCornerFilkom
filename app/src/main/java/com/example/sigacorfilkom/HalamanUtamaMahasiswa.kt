package com.example.sigacorfilkom

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.time.LocalDate

class HalamanUtamaMahasiswa(
    kontrolJadwal: KontrolJadwal,
    kontrolLogout: KontrolLogout,
    kontrolPanduan: KontrolPanduan
) : ViewModel() {

    private val namaMahasiswa = mutableStateOf("")
    private val tanggal = mutableStateOf("")
    private val bulan = mutableStateOf("")
    private val tahun = mutableStateOf("")

    private val kontrolJadwal: KontrolJadwal
    private val kontrolLogout: KontrolLogout
    private val kontrolPanduan: KontrolPanduan

    init {
        this.kontrolJadwal = kontrolJadwal
        this.kontrolLogout = kontrolLogout
        this.kontrolPanduan = kontrolPanduan

        namaMahasiswa.value = Otentikasi.getMahasiswa()!!.getNama()

        val now = LocalDate.now()
        tanggal.value = now.dayOfMonth.toString()
        bulan.value = now.month.value.toString()
        tahun.value = now.year.toString()
    }

    /**
     *  EVENT lihatJadwal
     */
    fun lihatJadwal() {
        viewModelScope.launch {
            /**
             *  CALL   tampilkanHalamanJadwal()
             *  TUJUAN (C) KontrolJadwal
             */
            kontrolJadwal.tampilkanHalamanJadwal()
        }
    }

    /**
     *  EVENT lihatPanduan
     */
    fun lihatPanduan() {
        /**
         *  CALL   tampilkanHalamanPanduan()
         *  TUJUAN (C) KontrolPanduan
         */
        kontrolPanduan.tampilkanHalamanPanduan()
    }

    fun getNamaMahasiswa() = namaMahasiswa.value

    fun getTanggal() = tanggal.value

    fun getBulan() = bulan.value

    fun getTahun() = tahun.value

    /**
     *  EVENT logout
     */
    fun logout() {
        /**
         *  CALL   logoutMahasiswa()
         *  TUJUAN (C) KontrolLogout
         */
        kontrolLogout.logoutMahasiswa()
    }
}