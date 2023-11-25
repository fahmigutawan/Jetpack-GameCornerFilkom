package com.example.sigacorfilkom.boundary_remove_this_later.panduan

import androidx.lifecycle.ViewModel
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolNavigasi

class HalamanPanduan(kontrolNavigasi: KontrolNavigasi): ViewModel() {
    private val kontrolNavigasi:KontrolNavigasi

    init {
        this.kontrolNavigasi = kontrolNavigasi
    }

    fun popHalaman() = kontrolNavigasi.popHalaman()
}