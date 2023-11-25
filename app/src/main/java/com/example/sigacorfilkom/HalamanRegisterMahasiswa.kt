package com.example.sigacorfilkom

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class HalamanRegisterMahasiswa(
    kontrolRegisterMahasiswa: KontrolRegisterMahasiswa
) : ViewModel() {
    private val nim = mutableStateOf("")
    private val nama = mutableStateOf("")
    private val password = mutableStateOf("")
    private val kontrolRegisterMahasiswa: KontrolRegisterMahasiswa

    init {
        this.kontrolRegisterMahasiswa = kontrolRegisterMahasiswa
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

    fun getNim() = nim

    fun getNama() = nama

    fun getPassword() = password

    /**
     *  EVENT submit
     */
    fun submit(
        onFailed: (String) -> Unit
    ) {
        /**
         *  CALL cekKosong
         */
        if (nim.value.isEmpty() || nama.value.isEmpty() || password.value.isEmpty()) {
            /**
             *  CALL tampilkan error kosong
             */
            onFailed("Semua data harus dimasukkan")
        } else {
            viewModelScope.launch {
                try {
                    /**
                     *  CALL   registerMahasiswa()
                     *  TUJUAN (C) KontrolRegisterMahasiswa
                     */
                    kontrolRegisterMahasiswa.registerMahasiswa(
                        nim.value,
                        nama.value,
                        password.value
                    )
                }
                /**
                 *  OPT exception
                 */
                catch (e: Exception) {
                    /**
                     *  CALL tampilkan error
                     */
                    onFailed(e.message.toString())
                }
            }
        }
    }
}