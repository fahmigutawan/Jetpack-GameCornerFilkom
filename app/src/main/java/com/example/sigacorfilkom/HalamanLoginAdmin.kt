package com.example.sigacorfilkom

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class HalamanLoginAdmin : ViewModel() {
    private var nip = mutableStateOf("")
    private var password = mutableStateOf("")

    fun getNip() = nip
    fun getPassword() = password
    fun setNip(value: String) {
        nip.value = value
    }
    fun setPassword(value: String) {
        password.value = value
    }

    fun login(
        onSuccess:() -> Unit,
        onFailed:(String) -> Unit
    ){
        if(nip.value.isEmpty() || password.value.isEmpty()){
            onFailed("Semua data harus dimasukkan")
        }else{
            KontrolOtentikasi.loginAdmin(
                nip.value,
                password.value,
                onSuccess, onFailed
            )
        }
    }
}