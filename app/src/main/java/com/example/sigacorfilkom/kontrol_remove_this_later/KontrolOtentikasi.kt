package com.example.sigacorfilkom.kontrol_remove_this_later

import com.example.sigacorfilkom.entity_remove_this_later.Admin
import com.example.sigacorfilkom.entity_remove_this_later.Mahasiswa
import com.google.firebase.firestore.FirebaseFirestore


class KontrolOtentikasi {
    companion object {
        private var mahasiswa = Mahasiswa()
        private var admin = Admin()

        fun loginMahasiswa(
            nim: String,
            password: String,
            onSuccess: () -> Unit,
            onFailed: (String) -> Unit
        ) {
            FirebaseFirestore
                .getInstance()
                .collection("mahasiswa")
                .document(nim)
                .addSnapshotListener { value, error ->
                    if (error != null) {
                        onFailed(error.message.toString())
                        return@addSnapshotListener
                    }

                    if (value?.data == null) {
                        onFailed("Lakukan Registrasi Terlebih Dahulu")
                        return@addSnapshotListener
                    }

                    value.data?.let { doc ->
                        mahasiswa.apply {
                            setNim(doc["nim"] as String)
                            setNama(doc["nama"] as String)
                            setPassword(doc["password"] as String)
                        }

                        if (mahasiswa.authenticate(password)) {
                            onSuccess()
                        } else {
                            onFailed("Password Salah")
                        }
                    }
                }
        }

        fun loginAdmin(
            nip: String,
            password: String,
            onSuccess: () -> Unit,
            onFailed: (String) -> Unit
        ) {
            FirebaseFirestore
                .getInstance()
                .collection("admin")
                .document(nip)
                .addSnapshotListener { value, error ->
                    if (error != null) {
                        onFailed(error.message.toString())
                        return@addSnapshotListener
                    }

                    if (value?.data == null) {
                        onFailed("Anda Tidak Terdaftar Sebagai Admin")
                        return@addSnapshotListener
                    }

                    value.data?.let { doc ->
                        admin.apply {
                            setNip(doc["nip"] as String)
                            setPassword(doc["password"] as String)
                        }

                        if (admin.authenticate(password)) {
                            onSuccess()
                        } else {
                            onFailed("Password Salah")
                        }
                    }
                }
        }

        fun registerMahasiswa(
            nim: String,
            nama: String,
            password: String,
            onSuccess: () -> Unit,
            onFailed: (String) -> Unit
        ) {
            //Cek apakah sudah terregistrasi
            FirebaseFirestore.getInstance()
                .collection("mahasiswa")
                .document(nim)
                .addSnapshotListener { value, error ->
                    value?.let {
                        if (it.data != null) {
                            onFailed("Akun sudah terdaftar, pakai identitas lain")
                            return@addSnapshotListener
                        }

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
                                mahasiswa.apply {
                                    setNim(nim)
                                    setPassword(password)
                                    setNama(nama)
                                }

                                onSuccess()
                                return@addOnSuccessListener
                            }.addOnFailureListener {
                                onFailed(it.message.toString())
                            }
                    }
                }
        }

        fun logout() {
            admin.resetField()
            mahasiswa.resetField()
        }

        fun getNimMahasiswa() = mahasiswa.getNim()

        fun getNamaMahasiswa() = mahasiswa.getNama()

        fun getNipAdmin() = admin.getNip()
    }
}