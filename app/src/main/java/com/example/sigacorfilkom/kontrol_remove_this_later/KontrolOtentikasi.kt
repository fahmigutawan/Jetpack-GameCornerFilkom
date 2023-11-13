package com.example.sigacorfilkom.kontrol_remove_this_later

import com.example.sigacorfilkom.entity_remove_this_later.Admin
import com.example.sigacorfilkom.entity_remove_this_later.Mahasiswa
import com.google.firebase.firestore.FirebaseFirestore


class KontrolOtentikasi {
    companion object {
        private var mahasiswa:Mahasiswa? = null
        private var admin:Admin? = null

        fun loginMahasiswa(
            nim: String,
            password: String,
            onSuccess: () -> Unit,
            onFailed: (String) -> Unit
        ) {
            if (nim.matches(Regex("\\d+"))) {
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

                        if (doc["nim"] as String == nim && doc["password"] as String == password) {
                            mahasiswa = Mahasiswa()
                            mahasiswa?.apply {
                                setNim(doc["nim"] as String)
                                setNama(doc["nama"] as String)
                                setPassword(doc["password"] as String)
                            }

                            onSuccess()
                            return@addOnSuccessListener
                        } else {
                            onFailed("NIM atau Password Salah atau Tidak Terdaftar")
                            return@addOnSuccessListener
                        }
                    }.addOnFailureListener {
                        onFailed(it.message.toString())
                        return@addOnFailureListener
                    }
            } else {
                onFailed("NIM Hanya boleh angka")
                return
            }
        }

        fun loginAdmin(
            nip: String,
            password: String,
            onSuccess: () -> Unit,
            onFailed: (String) -> Unit
        ) {
            if (nip.matches(Regex("\\d+"))) {
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

                        if (doc["nip"] as String == nip && doc["password"] as String == password) {
                            admin = Admin()
                            admin?.apply {
                                setNip(doc["nip"] as String)
                                setPassword(doc["password"] as String)
                            }
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
            } else {
                onFailed("NIP Hanya boleh angka")
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
            if (nim.matches(Regex("\\d+"))) {
                if(nim.length != 15){
                    onFailed("Masukkan NIM yang benar")
                    return
                }

                if(nim.substring(2, 5) != "515"){
                    onFailed("Hanya mahasiswa FILKOM yang bisa mendaftar")
                    return
                }

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
                                .set(
                                    mapOf(
                                        "nim" to nim,
                                        "nama" to nama,
                                        "password" to password
                                    )
                                ).addOnSuccessListener {
                                    mahasiswa = Mahasiswa()
                                    mahasiswa?.apply {
                                        setNim(nim)
                                        setPassword(password)
                                        setNama(nama)
                                    }

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
            } else {
                onFailed("NIM Hanya boleh angka")
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
}