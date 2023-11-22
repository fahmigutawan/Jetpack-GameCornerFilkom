package com.example.sigacorfilkom.kontrol_remove_this_later

import androidx.navigation.NavController
import com.example.sigacorfilkom.boundary_remove_this_later.otentikasi.Otentikasi
import com.example.sigacorfilkom.entity_remove_this_later.Mahasiswa
import com.google.firebase.firestore.FirebaseFirestore

class KontrolLoginMahasiswa(navigasi: NavController) {
    private val navigasi: NavController

    init {
        this.navigasi = navigasi
    }

    fun tampilkanHalamanLoginMahasiswa() {
        navigasi.navigate("login_mahasiswa")
    }

    fun loginMahasiswa(
        nim: String,
        password: String,
        onFailed: (String) -> Unit
    ) {
        try {
            val mahasiswa = Mahasiswa(
                nim,
                password
            )

            mahasiswa.validateNimIsNumber()
            mahasiswa.validateInputtedData()

            FirebaseFirestore
                .getInstance()
                .collection("mahasiswa")
                .document(nim)
                .get()
                .addOnSuccessListener { doc ->
                    if (doc.data == null) {
                        onFailed("NIM atau Password Salah atau Tidak Terdaftar")
                        return@addOnSuccessListener
                    }

                    if (mahasiswa.validatePassword(doc["password"] as String)) {
                        mahasiswa.setNama(doc["nama"] as String)
                        Otentikasi.setMahasiswa(mahasiswa)
                        navigasi.navigate("home_mahasiswa")
                        return@addOnSuccessListener
                    } else {
                        onFailed("NIM atau Password Salah atau Tidak Terdaftar")
                        return@addOnSuccessListener
                    }
                }.addOnFailureListener {
                    onFailed(it.message.toString())
                    return@addOnFailureListener
                }
        } catch (e: Exception) {
            onFailed(e.message.toString())
            return
        }
    }
}