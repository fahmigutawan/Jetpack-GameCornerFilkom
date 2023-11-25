package com.example.sigacorfilkom

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolLoginMahasiswa
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
         *  CALL cekStatusKosong
         */
        if (nim.value.isEmpty() || password.value.isEmpty()) {
            /**
             *  CALL tampilkan pesan kosong
             */
            onFailed("Semua data harus dimasukkan")
        } else {
            viewModelScope.launch {
                /**
                 *  CALL   login()
                 *  TUJUAN (C) KontrolLoginMahasiswa
                 */
                kontrolLoginMahasiswa.login(
                    nim.value,
                    password.value,
                    onFailed
                )
            }
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