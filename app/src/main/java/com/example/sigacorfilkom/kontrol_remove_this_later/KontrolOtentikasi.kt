package com.example.sigacorfilkom.kontrol_remove_this_later

import com.example.sigacorfilkom.entity_remove_this_later.Admin
import com.example.sigacorfilkom.entity_remove_this_later.Mahasiswa
import com.google.firebase.firestore.FirebaseFirestore


class KontrolOtentikasi {
    private var mahasiswa: Mahasiswa? = null
    private var admin: Admin? = null

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
        onSuccess: () -> Unit,
        onFailed: (String) -> Unit
    ) {
//        val now = Date().time
        try {
            val mahasiswa = Mahasiswa(
                nim = nim,
                nama = nama,
                password = password
            )

            mahasiswa.validateNimIsNumber()
            mahasiswa.validateInputtedData()

            FirebaseFirestore.getInstance()
                .collection("mahasiswa")
                .document(nim)
                .get()
                .addOnSuccessListener {
                    if (it.data != null) {
                        onFailed("Akun sudah terdaftar, pakai identitas lain")
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
                            }.addOnFailureListener {
                                onFailed(it.message.toString())
                                return@addOnFailureListener
                            }
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

    fun logout() {
        admin = null
        mahasiswa = null
    }

    fun getNimMahasiswa() = mahasiswa?.getNim() ?: ""

    fun getNamaMahasiswa() = mahasiswa?.getNama() ?: ""

    fun isAdmin() = admin != null
}