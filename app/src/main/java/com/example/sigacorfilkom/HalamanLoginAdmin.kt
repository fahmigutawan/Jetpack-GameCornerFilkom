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

    /**
     *  Event login
     */
    fun login(
        onFailed: (String) -> Unit
    ) {
        /**
         *  CALL cekKosong
         */
        /**
         *  OPT  statusKosong = true
         */
        if (nip.value.isEmpty() || password.value.isEmpty()) {
            /**
             *  CALL tampilkan error kosong
             */
            onFailed("Semua data harus dimasukkan")
            return
        }

        viewModelScope.launch {
            /**
             *  CALL   login()
             *  TUJUAN (C) KontrolLoginAdmin
             */
            kontrolLoginAdmin.login(
                nip.value,
                password.value,
                onFailed
            )
        }
    }
}