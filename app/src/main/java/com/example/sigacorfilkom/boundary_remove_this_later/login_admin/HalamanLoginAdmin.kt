package com.example.sigacorfilkom.boundary_remove_this_later.login_admin

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolOtentikasi
import kotlinx.coroutines.flow.MutableStateFlow


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
        KontrolOtentikasi.loginAdmin(
            nip.value,
            password.value,
            onSuccess, onFailed
        )
    }
}