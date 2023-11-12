package com.example.sigacorfilkom

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class HalamanLoginMahasiswa : ViewModel() {
    private var nim = mutableStateOf("")
    private var password = mutableStateOf("")

    fun getNim() = nim
    fun getPassword() = password
    fun setNim(value: String) {
        nim.value = value
    }
    fun setPassword(value: String) {
        password.value = value
    }

    fun login(
        onSuccess:() -> Unit,
        onFailed:(String) -> Unit
    ){
        if(nim.value.isEmpty() || password.value.isEmpty()){
            onFailed("Semua data harus dimasukkan")
        }else{
            KontrolOtentikasi.loginMahasiswa(
                nim.value,
                password.value,
                onSuccess, onFailed
            )
        }
    }
}