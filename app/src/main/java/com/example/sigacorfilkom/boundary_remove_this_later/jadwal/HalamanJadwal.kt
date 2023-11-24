package com.example.sigacorfilkom.boundary_remove_this_later.jadwal

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sigacorfilkom.SnackbarHandler
import com.example.sigacorfilkom.entity_remove_this_later.Hari
import com.example.sigacorfilkom.entity_remove_this_later.Perangkat
import com.example.sigacorfilkom.entity_remove_this_later.Sesi
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolJadwal
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolOtentikasi
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolReservasi
import kotlinx.coroutines.launch
import java.lang.Exception


class HalamanJadwal(
    kontrolJadwal: KontrolJadwal,
    kontrolReservasi: KontrolReservasi,
    kontrolOtentikasi: KontrolOtentikasi
) : ViewModel() {
    private var daftarHari = mutableStateListOf<Hari>()
    private var daftarPerangkat = mutableStateListOf<Perangkat>()
    private var daftarSesi = mutableStateListOf<Sesi>()
    private var pickedHari = mutableStateOf<Hari?>(null)
    private var pickedPerangkat = mutableStateOf<Perangkat?>(null)
    private var pickedSesi = mutableStateOf<Sesi?>(null)
    private val kontrolJadwal: KontrolJadwal
    private val kontrolReservasi: KontrolReservasi
    private val kontrolOtentikasi: KontrolOtentikasi
    private val showReservasiBerhasilDialog = mutableStateOf(false)

    init {
        this.kontrolJadwal = kontrolJadwal
        this.kontrolReservasi = kontrolReservasi
        this.kontrolOtentikasi = kontrolOtentikasi
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
                val mDaftarSesi = kontrolJadwal.getDaftarSesi(
                    tanggal = pickedHari.value!!.getTanggal(),
                    bulan = pickedHari.value!!.getBulan(),
                    tahun = pickedHari.value!!.getTahun(),
                    idPerangkat = pickedPerangkat.value!!.getIdPerangkat()
                )

                daftarSesi.clear()
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