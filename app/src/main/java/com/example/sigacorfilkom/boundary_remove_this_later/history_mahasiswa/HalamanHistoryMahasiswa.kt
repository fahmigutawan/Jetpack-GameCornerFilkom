package com.example.sigacorfilkom.boundary_remove_this_later.history_mahasiswa

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.sigacorfilkom.entity_remove_this_later.Reservasi

class HalamanHistoryMahasiswa : ViewModel() {
    val reservasiBelumLewat = mutableStateListOf<Reservasi>()
}