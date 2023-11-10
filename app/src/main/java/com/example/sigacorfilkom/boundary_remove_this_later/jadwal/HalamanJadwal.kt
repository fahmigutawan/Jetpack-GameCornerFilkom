package com.example.sigacorfilkom.boundary_remove_this_later.jadwal

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sigacorfilkom.entity_remove_this_later.Hari
import com.example.sigacorfilkom.entity_remove_this_later.Perangkat
import com.example.sigacorfilkom.entity_remove_this_later.Sesi
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolJadwal
import kotlinx.coroutines.launch


class HalamanJadwal : ViewModel() {
    private val kotrolJadwal = KontrolJadwal()
    private var listHari = mutableStateListOf<Hari>()
    private var listPerangkat = mutableStateListOf<Perangkat>()
    private var listSesi = mutableStateListOf<Sesi>()
    private var pickedHari = mutableStateOf<Hari?>(null)
    private var pickedPerangkat = mutableStateOf<Perangkat?>(null)
    private var pickedSesi = mutableStateOf<Sesi?>(null)

    init {
        listHari.addAll(kotrolJadwal.getHari())
        viewModelScope.launch {
            kotrolJadwal.getPerangkat().collect{
                listPerangkat.clear()
                listPerangkat.addAll(it)
            }
        }
    }

    fun loadSesi(){
        if(pickedHari.value != null && pickedPerangkat.value != null) {
            viewModelScope.launch {
                kotrolJadwal.getSesi(
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

    fun getListHari() = listHari

    fun getListPerangkat() = listPerangkat

    fun getListSesi() = listSesi

    fun setPickedHari(value:Hari?){
        pickedHari.value = value
    }

    fun getPickedHari() = pickedHari

    fun getPickedPerangkat() = pickedPerangkat

    fun setPickedPerangkat(value:Perangkat?){
        pickedPerangkat.value = value
    }

    fun setPickedSesi(value:Sesi?){
        pickedSesi.value = value
    }

    fun getPickedSesi() = pickedSesi
}