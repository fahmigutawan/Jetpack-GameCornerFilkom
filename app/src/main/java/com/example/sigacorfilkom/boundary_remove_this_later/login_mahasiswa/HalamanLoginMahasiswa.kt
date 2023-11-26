package com.example.sigacorfilkom.boundary_remove_this_later.login_mahasiswa

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolNavigasi
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolOtentikasi
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolSnackbar
import kotlinx.coroutines.flow.MutableStateFlow


class HalamanLoginMahasiswa(
    kontrolOtentikasi: KontrolOtentikasi,
    kontrolNavigasi: KontrolNavigasi,
    kontrolSnackbar: KontrolSnackbar
) : ViewModel() {
    private var nim = mutableStateOf("")
    private var password = mutableStateOf("")
    private var kontrolOtentikasi:KontrolOtentikasi
    private val kontrolNavigasi:KontrolNavigasi
    private val kontrolSnackbar: KontrolSnackbar

    init{
        this.kontrolOtentikasi = kontrolOtentikasi
        this.kontrolNavigasi= kontrolNavigasi
        this.kontrolSnackbar = kontrolSnackbar
    }

    fun getNim() = nim
    fun getPassword() = password
    fun setNim(value: String) {
        nim.value = value
    }
    fun setPassword(value: String) {
        password.value = value
    }

    fun validate() = nim.value.isNotEmpty() && password.value.isNotEmpty()

    fun login(
        onSuccess:() -> Unit
    ){
        if(!validate()){
            kontrolSnackbar.showSnackbar("Semua data harus dimasukkan")
        }else{
            kontrolOtentikasi.loginMahasiswa(
                nim.value,
                password.value,
                onSuccess
            )
        }
    }

    fun navigasiKeRegister() = kontrolNavigasi.navigasiKeRegister()

    fun navigasiKeHomeMahasiswa() = kontrolNavigasi.navigasiKeHomeMahasiswa()
}