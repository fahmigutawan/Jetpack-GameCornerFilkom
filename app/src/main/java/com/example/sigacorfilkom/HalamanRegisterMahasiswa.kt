package com.example.sigacorfilkom

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class HalamanRegisterMahasiswa : ViewModel() {
    private val nim = mutableStateOf("")
    private val nama = mutableStateOf("")
    private val password = mutableStateOf("")

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
        if(nim.value.isEmpty() || nama.value.isEmpty() || password.value.isEmpty()){
            onFailed("Semua data harus dimasukkan")
        }else{
            KontrolOtentikasi.registerMahasiswa(
                nim.value,
                nama.value,
                password.value,
                onSuccess,
                onFailed
            )
        }
    }
}