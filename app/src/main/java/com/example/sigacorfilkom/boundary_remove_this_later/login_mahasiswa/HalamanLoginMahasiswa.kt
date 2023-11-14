package com.example.sigacorfilkom.boundary_remove_this_later.login_mahasiswa

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sigacorfilkom.kontrolOtentikasi
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolOtentikasi
import kotlinx.coroutines.flow.MutableStateFlow


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
            kontrolOtentikasi.loginMahasiswa(
                nim.value,
                password.value,
                onSuccess, onFailed
            )
        }
    }
}