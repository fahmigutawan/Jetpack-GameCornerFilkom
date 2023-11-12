package com.example.sigacorfilkom

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class HalamanJadwal : ViewModel() {
    private var listHari = mutableStateListOf<Hari>()
    private var listPerangkat = mutableStateListOf<Perangkat>()
    private var listSesi = mutableStateListOf<Sesi>()
    private var pickedHari = mutableStateOf<Hari?>(null)
    private var pickedPerangkat = mutableStateOf<Perangkat?>(null)
    private var pickedSesi = mutableStateOf<Sesi?>(null)

    init {
        listHari.addAll(KontrolJadwal.getHari())
        viewModelScope.launch {
            KontrolJadwal.getPerangkat().collect{
                listPerangkat.clear()
                listPerangkat.addAll(it)
            }
        }
    }

    fun loadSesi(){
        if(pickedHari.value != null && pickedPerangkat.value != null) {
            viewModelScope.launch {
                KontrolJadwal.getSesi(
                    tanggal = pickedHari.value!!.getTanggal(),
                    bulan = pickedHari.value!!.getBulan(),
                    tahun = pickedHari.value!!.getTahun(),
                    idPerangkat = pickedPerangkat.value!!.getIdPerangkat()
                ).collect{
                    listSesi.clear()
                    listSesi.addAll(it)
                }
            }
        }
    }

    fun reservasi(
        onSuccess:() -> Unit,
        onFailed:(String) -> Unit
    ){
        KontrolReservasi.buatReservasi(
            nimPeminjam = KontrolOtentikasi.getNimMahasiswa(),
            nomorSesi = pickedSesi.value?.getSesiNumber() ?: 0,
            idPerangkat = pickedPerangkat.value?.getIdPerangkat() ?: "...",
            tanggal = pickedHari.value?.getTanggal() ?: 0,
            bulan = pickedHari.value?.getBulan() ?: 0,
            tahun = pickedHari.value?.getTahun() ?: 0,
            onSuccess = onSuccess,
            onFailed = onFailed
        )
    }

    fun loadHari() = KontrolJadwal.loadHari()

    fun getListHari() = listHari

    fun getListPerangkat() = listPerangkat

    fun getListSesi() = listSesi

    fun setPickedHari(value: Hari?){
        pickedHari.value = value
    }

    fun getPickedHari() = pickedHari

    fun getPickedPerangkat() = pickedPerangkat

    fun setPickedPerangkat(value: Perangkat?){
        pickedPerangkat.value = value
    }

    fun setPickedSesi(value: Sesi?){
        pickedSesi.value = value
    }

    fun getPickedSesi() = pickedSesi
}