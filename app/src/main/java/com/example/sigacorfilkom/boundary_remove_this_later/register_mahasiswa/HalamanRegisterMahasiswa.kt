package com.example.sigacorfilkom.boundary_remove_this_later.register_mahasiswa

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolOtentikasi


class HalamanRegisterMahasiswa : ViewModel() {
    val nim = mutableStateOf("")
    val nama = mutableStateOf("")
    val password = mutableStateOf("")

    fun setNim(value: String) {
        nim.value = value
    }

    fun setNama(value: String) {
        nama.value = value
    }

    fun setPassword(value: String) {
        password.value = value
    }

    fun getNim() = nim.value

    fun getNama() = nama.value

    fun getPassword() = password.value

    fun register(
        onSuccess: () -> Unit,
        onFailed: (String) -> Unit
    ) {
        KontrolOtentikasi.registerMahasiswa(
            nim.value,
            nama.value,
            password.value,
            onSuccess,
            onFailed
        )
    }
}