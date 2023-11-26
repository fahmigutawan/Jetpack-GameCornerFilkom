package com.example.sigacorfilkom

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception


class HalamanJadwal(
    kontrolJadwal: KontrolJadwal,
    kontrolReservasi: KontrolReservasi,
    kontrolRegisterMahasiswa: KontrolRegisterMahasiswa
) : ViewModel() {
    private var daftarHari = mutableStateListOf<Hari>()
    private var daftarPerangkat = mutableStateListOf<Perangkat>()
    private var daftarSesi = mutableStateListOf<Sesi>()
    private var pickedHari = mutableStateOf<Hari?>(null)
    private var pickedPerangkat = mutableStateOf<Perangkat?>(null)
    private var pickedSesi = mutableStateOf<Sesi?>(null)
    private val kontrolJadwal: KontrolJadwal
    private val kontrolReservasi: KontrolReservasi
    private val kontrolRegisterMahasiswa: KontrolRegisterMahasiswa
    private val showReservasiBerhasilDialog = mutableStateOf(false)

    init {
        this.kontrolJadwal = kontrolJadwal
        this.kontrolReservasi = kontrolReservasi
        this.kontrolRegisterMahasiswa = kontrolRegisterMahasiswa
    }

    fun setDaftarHari(daftarHari: List<Hari>) {
        this.daftarHari.clear()
        this.daftarHari.addAll(daftarHari)
    }

    fun setDaftarPerangkat(daftarPerangkat: List<Perangkat>) {
        this.daftarPerangkat.clear()
        this.daftarPerangkat.addAll(daftarPerangkat)
    }

    fun getShowReservasiBerhasilDialog(): MutableState<Boolean> {
        return showReservasiBerhasilDialog
    }

    fun setShowReservasiBerhasilDialog(show: Boolean) {
        showReservasiBerhasilDialog.value = show
    }

    fun submitHariDanPerangkat() {
        if (pickedHari.value != null && pickedPerangkat.value != null) {
            viewModelScope.launch {
                daftarSesi.clear()
                val mDaftarSesi = kontrolJadwal.getDaftarSesi(
                    tanggal = pickedHari.value!!.getTanggal(),
                    bulan = pickedHari.value!!.getBulan(),
                    tahun = pickedHari.value!!.getTahun(),
                    idPerangkat = pickedPerangkat.value!!.getIdPerangkat()
                )
                daftarSesi.addAll(mDaftarSesi)
            }
        }
    }

    fun reservasi() {
        viewModelScope.launch {
            try {
                /**
                 *  CALL   buatReservasi(nimPeminjam, nomorSesi, idPerangkat, tanggal, bulan, tahun)
                 *  TUJUAN (C) KontrolRESERVASI
                 *  RETURN boolean
                 */
                val hasilSimpan = kontrolReservasi.buatReservasi(
                    nomorSesi = pickedSesi.value?.getSesiNumber() ?: 0,
                    idPerangkat = pickedPerangkat.value?.getIdPerangkat() ?: "...",
                    tanggal = pickedHari.value?.getTanggal() ?: 0,
                    bulan = pickedHari.value?.getBulan() ?: 0,
                    tahun = pickedHari.value?.getTahun() ?: 0,
                )

                if(hasilSimpan) {
                    /**
                     *  CALL tampilkan sukses
                     */
                    setShowReservasiBerhasilDialog(true)
                } else {
                    /**
                     *  CALL   tampilkan error
                     */
                    SnackbarHandler.showSnackbar("Gagal menyimpan reservasi")
                }
            } catch (e: Exception) {
                /**
                 *  CALL   tampilkan error
                 */
                SnackbarHandler.showSnackbar(e.message ?: "")
            }
        }

    }

    fun getListHari() = daftarHari

    fun getListPerangkat() = daftarPerangkat

    fun getListSesi() = daftarSesi

    fun setPickedHari(value: Hari?) {
        pickedHari.value = value
    }

    fun getPickedHari() = pickedHari

    fun getPickedPerangkat() = pickedPerangkat

    fun setPickedPerangkat(value: Perangkat?) {
        pickedPerangkat.value = value
    }

    fun setPickedSesi(value: Sesi?) {
        pickedSesi.value = value
    }

    fun getPickedSesi() = pickedSesi
}