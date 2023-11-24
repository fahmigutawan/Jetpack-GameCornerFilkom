package com.example.sigacorfilkom.boundary_remove_this_later.login_mahasiswa

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolLoginMahasiswa
import kotlinx.coroutines.launch


class HalamanLoginMahasiswa(
    kontrolLoginMahasiswa: KontrolLoginMahasiswa
) : ViewModel() {
    private var nim = mutableStateOf("")
    private var password = mutableStateOf("")
    private val kontrolLoginMahasiswa: KontrolLoginMahasiswa

    init {
        this.kontrolLoginMahasiswa = kontrolLoginMahasiswa
    }

    fun getNim() = nim
    fun getPassword() = password
    fun setNim(value: String) {
        nim.value = value
    }

    fun setPassword(value: String) {
        password.value = value
    }

    fun login(
        onFailed: (String) -> Unit
    ) {
        if (nim.value.isEmpty() || password.value.isEmpty()) {
            /**
             *  CALL tampilkan pesan error
             */
            onFailed("Semua data harus dimasukkan")
        } else {
            viewModelScope.launch {
                kontrolLoginMahasiswa.login(
                    nim.value,
                    password.value,
                    onFailed
                )
            }
        }
    }
}