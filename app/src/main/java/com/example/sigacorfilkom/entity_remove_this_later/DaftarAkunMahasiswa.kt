package com.example.sigacorfilkom.entity_remove_this_later

import com.example.sigacorfilkom.Mahasiswa
import com.example.sigacorfilkom.boundary_remove_this_later.otentikasi.Otentikasi
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CompletableDeferred

class DaftarAkunMahasiswa {
    suspend fun validasiAkunMahasiswa(nim: String, password: String): Boolean {
        var hasilValidasi = false

        val mahasiswa = Mahasiswa(
            nim,
            password
        )

        mahasiswa.validateNimIsNumber()
        mahasiswa.validateNimFilkom()

        val callAsyncWaiter = CompletableDeferred<Unit>()
        FirebaseFirestore
            .getInstance()
            .collection("mahasiswa")
            .document(nim)
            .get()
            .addOnSuccessListener { doc ->
                if (doc.data == null) {
                    hasilValidasi = false
                    callAsyncWaiter.complete(Unit)
                } else {
                    if (mahasiswa.validatePassword(doc["password"] as String)) {
                        mahasiswa.setNama(doc["nama"] as String)
                        Otentikasi.setMahasiswa(mahasiswa)

                        hasilValidasi = true
                        callAsyncWaiter.complete(Unit)
                    } else {
                        hasilValidasi = false
                        callAsyncWaiter.complete(Unit)
                    }
                }
            }
            .addOnFailureListener { e ->
                callAsyncWaiter.completeExceptionally(e)
            }
        callAsyncWaiter.await()

        return hasilValidasi
    }

    suspend fun buatAkunMahasiswa(mahasiswa: Mahasiswa) {

        // CEK Apakah Mahasiswa Telah Terdaftar
        var hasilCekMahasiswa: Mahasiswa? = null

        var callAsyncWaiter = CompletableDeferred<Unit>()
        FirebaseFirestore.getInstance()
            .collection("mahasiswa")
            .document(mahasiswa.getNim())
            .get()
            .addOnSuccessListener { doc ->
                if (doc.data != null) {
                    hasilCekMahasiswa = Mahasiswa(
                        doc["nim"] as String,
                        doc["password"] as String,
                        doc["nama"] as String
                    )
                }
                callAsyncWaiter.complete(Unit)

            }
            .addOnFailureListener { e ->
                callAsyncWaiter.completeExceptionally(e)
            }
        callAsyncWaiter.await()

        if (hasilCekMahasiswa != null) {
            throw Exception("Akun sudah terdaftar, pakai identitas lain")
        }

        // BUAT akun mahasiswa
        callAsyncWaiter = CompletableDeferred(Unit)
        FirebaseFirestore.getInstance()
            .collection("mahasiswa")
            .document(mahasiswa.getNim())
            .set(mahasiswa)
            .addOnSuccessListener {
                callAsyncWaiter.complete(Unit)
            }.addOnFailureListener { e ->
                callAsyncWaiter.completeExceptionally(e)
            }
        callAsyncWaiter.await()

        // Set otentikasi
        Otentikasi.setMahasiswa(mahasiswa)
    }
}