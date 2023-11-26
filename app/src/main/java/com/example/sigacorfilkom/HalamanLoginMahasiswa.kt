package com.example.sigacorfilkom

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class HalamanLoginMahasiswa(
    kontrolLoginMahasiswa: KontrolLoginMahasiswa,
    kontrolRegisterMahasiswa: KontrolRegisterMahasiswa
) : ViewModel() {
    private var nim = mutableStateOf("")
    private var password = mutableStateOf("")
    private val kontrolLoginMahasiswa: KontrolLoginMahasiswa
    private val kontrolRegisterMahasiswa: KontrolRegisterMahasiswa

    init {
        this.kontrolLoginMahasiswa = kontrolLoginMahasiswa
        this.kontrolRegisterMahasiswa = kontrolRegisterMahasiswa
    }

    fun getNim() = nim
    fun getPassword() = password
    fun setNim(value: String) {
        nim.value = value
    }

    fun setPassword(value: String) {
        password.value = value
    }

    /**
     *  EVENT login
     */
    fun login(
        onFailed: (String) -> Unit
    ) {
        /**
         *  CALL cekKosong
         */
        /**
         *  OPT statusKosong = true
         */
        if (nim.value.isEmpty() || password.value.isEmpty()) {
            /**
             *  CALL tampilkan pesan kosong
             */
            onFailed("Semua data harus dimasukkan")
            return
        }

        viewModelScope.launch {
            /**
             *  CALL   login(nim, password, onFailed)
             *  TUJUAN (C) KontrolLoginMahasiswa
             */
            kontrolLoginMahasiswa.login(
                nim.value,
                password.value,
                onFailed
            )
        }
    }

    /**
     *  EVENT register
     */
    fun register() {
        /**
         *  CALL   tampilkanRegisterMahasiswa()
         *  TUJUAN (C) KontrolRegisterMahasiswa
         */
        kontrolRegisterMahasiswa.tampilkanRegisterMahasiswa()
    }
}