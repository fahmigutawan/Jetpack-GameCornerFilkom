package com.example.sigacorfilkom.kontrol_remove_this_later

import android.util.Log
import com.example.sigacorfilkom.entity_remove_this_later.Admin
import com.example.sigacorfilkom.entity_remove_this_later.Mahasiswa
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date


class KontrolOtentikasi(
    kontrolSnackbar: KontrolSnackbar
) {
    private var mahasiswa: Mahasiswa? = null
    private var admin: Admin? = null
    private val kontrolSnackbar:KontrolSnackbar

    init {
        this.kontrolSnackbar = kontrolSnackbar
    }

    fun loginMahasiswa(
        nim: String,
        password: String,
        onSuccess: () -> Unit,
        onFailed: (String) -> Unit
    ) {
        val mahasiswa = Mahasiswa(
            nim,
            password
        )

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
                    this.mahasiswa = mahasiswa
                    onSuccess()
                    return@addOnSuccessListener
                } else {
                    onFailed("NIM atau Password Salah atau Tidak Terdaftar")
                    return@addOnSuccessListener
                }
            }
            .addOnFailureListener {
                onFailed(it.message.toString())
                return@addOnFailureListener
            }
    }

    fun loginAdmin(
        nip: String,
        password: String,
        onSuccess: () -> Unit,
        onFailed: (String) -> Unit
    ) {
        try {
            val admin = Admin(
                nip,
                password
            )

            admin.validateNipIsNumber()

            FirebaseFirestore
                .getInstance()
                .collection("admin")
                .document(nip)
                .get()
                .addOnSuccessListener { doc ->
                    if (doc.data == null) {
                        onFailed("NIP tidak terdaftar sebagai admin")
                        return@addOnSuccessListener
                    }

                    if (admin.validatePassword(doc["password"] as String)) {
                        this.admin = admin
                        onSuccess()
                        return@addOnSuccessListener
                    } else {
                        onFailed("Password salah")
                        return@addOnSuccessListener
                    }
                }
                .addOnFailureListener {
                    onFailed(it.message.toString())
                    return@addOnFailureListener
                }
        } catch (e: Exception) {
            onFailed(e.message.toString())
            return
        }
    }

    fun registerMahasiswa(
        nim: String,
        nama: String,
        password: String,
        onSuccess: () -> Unit
    ) {
        val mahasiswa = Mahasiswa(
            nim = nim,
            nama = nama,
            password = password
        )

        if(!mahasiswa.validateNimIsNumber()){
            kontrolSnackbar.showSnackbar("NIM hanya boleh angka")
            return
        }

        if(!mahasiswa.validateNimIs15Digit()){
            kontrolSnackbar.showSnackbar("Masukka NIM yang benar")
            return
        }

        if(!mahasiswa.validateNimIsFilkom()){
            kontrolSnackbar.showSnackbar("Hanya mahasiswa FILKOM UB yang bisa mendaftar")
            return
        }

        FirebaseFirestore.getInstance()
            .collection("mahasiswa")
            .document(nim)
            .get()
            .addOnSuccessListener {
                if (it.data != null) {
                    kontrolSnackbar.showSnackbar("Akun sudah terdaftar, pakai identitas lain")
                    return@addOnSuccessListener
                } else {
                    FirebaseFirestore.getInstance()
                        .collection("mahasiswa")
                        .document(nim)
                        .set(mahasiswa)
                        .addOnSuccessListener {
                            this.mahasiswa = mahasiswa
                            onSuccess()
                            return@addOnSuccessListener
                        }
                        .addOnFailureListener {
                            kontrolSnackbar.showSnackbar(it.message.toString())
                        }
                }
            }
            .addOnFailureListener {
                kontrolSnackbar.showSnackbar(it.message.toString())
            }
    }

    fun logout() {
        admin = null
        mahasiswa = null
    }

    fun getNimMahasiswa() = mahasiswa?.getNim() ?: ""

    fun getNamaMahasiswa() = mahasiswa?.getNama() ?: ""

    fun isAdmin() = admin != null
}