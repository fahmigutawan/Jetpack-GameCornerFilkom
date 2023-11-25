package com.example.sigacorfilkom.boundary_remove_this_later.register_mahasiswa

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolNavigasi
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolOtentikasi
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolSnackbar


class HalamanRegisterMahasiswa(
    kontrolOtentikasi: KontrolOtentikasi,
    kontrolNavigasi: KontrolNavigasi,
    kontrolSnackbar: KontrolSnackbar
) : ViewModel() {
    private val nim = mutableStateOf("")
    private val nama = mutableStateOf("")
    private val password = mutableStateOf("")
    private val kontrolOtentikasi:KontrolOtentikasi
    private val kontrolNavigasi:KontrolNavigasi
    private val kontrolSnackbar:KontrolSnackbar

    init {
        this.kontrolOtentikasi = kontrolOtentikasi
        this.kontrolNavigasi = kontrolNavigasi
        this.kontrolSnackbar = kontrolSnackbar
    }

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

    fun validate() = nim.value.isNotEmpty() && nama.value.isNotEmpty() && password.value.isNotEmpty()

    fun register(
        onSuccess: () -> Unit
    ) {
        if(!validate()){
            kontrolSnackbar.showSnackbar("Semua data harus dimasukkan")
        }else{
            kontrolOtentikasi.registerMahasiswa(
                nim.value,
                nama.value,
                password.value,
                onSuccess
            )
        }
    }

    fun navigasiKeLoginMahasiswa() = kontrolNavigasi.navigasiKeLoginMahasiswa()

    fun navigasiKeHomeMahasiswa() = kontrolNavigasi.navigasiKeHomeMahasiswa()

}