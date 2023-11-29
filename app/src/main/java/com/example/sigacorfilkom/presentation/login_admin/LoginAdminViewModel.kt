package com.example.sigacorfilkom.presentation.login_admin

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sigacorfilkom.repoOtentikasi


class LoginAdminViewModel : ViewModel() {
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
            repoOtentikasi.loginAdmin(
                nip.value,
                password.value,
                onSuccess, onFailed
            )
        }
    }
}