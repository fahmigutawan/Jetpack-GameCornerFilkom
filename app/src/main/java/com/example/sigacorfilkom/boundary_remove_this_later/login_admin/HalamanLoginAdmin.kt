package com.example.sigacorfilkom.boundary_remove_this_later.login_admin

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolNavigasi
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolOtentikasi
import kotlinx.coroutines.flow.MutableStateFlow


class HalamanLoginAdmin(
    kontrolOtentikasi: KontrolOtentikasi,
    kontrolNavigasi: KontrolNavigasi
) : ViewModel() {
    private var nip = mutableStateOf("")
    private var password = mutableStateOf("")
    private var kontrolOtentikasi:KontrolOtentikasi
    private val kontrolNavigasi:KontrolNavigasi

    init{
        this.kontrolOtentikasi = kontrolOtentikasi
        this.kontrolNavigasi = kontrolNavigasi
    }

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
            kontrolOtentikasi.loginAdmin(
                nip.value,
                password.value,
                onSuccess, onFailed
            )
        }
    }

    fun navigasiKeHomeAdmin() = kontrolNavigasi.navigasiKeHomeAdmin()
}