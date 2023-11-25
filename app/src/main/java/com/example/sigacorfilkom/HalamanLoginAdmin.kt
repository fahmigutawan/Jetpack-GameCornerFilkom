package com.example.sigacorfilkom

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class HalamanLoginAdmin(
    kontrolLoginAdmin: KontrolLoginAdmin
) : ViewModel() {
    private var nip = mutableStateOf("")
    private var password = mutableStateOf("")
    private var kontrolLoginAdmin: KontrolLoginAdmin

    init {
        this.kontrolLoginAdmin = kontrolLoginAdmin
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
        onFailed: (String) -> Unit
    ) {
        if (nip.value.isEmpty() || password.value.isEmpty()) {
            onFailed("Semua data harus dimasukkan")
        } else {
            viewModelScope.launch {
                kontrolLoginAdmin.login(
                    nip.value,
                    password.value,
                    onFailed
                )
            }
        }
    }
}