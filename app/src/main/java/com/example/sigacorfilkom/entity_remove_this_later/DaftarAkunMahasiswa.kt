package com.example.sigacorfilkom.entity_remove_this_later

import com.example.sigacorfilkom.boundary_remove_this_later.otentikasi.Otentikasi
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CompletableDeferred

class DaftarAkunMahasiswa {
    suspend fun validasiAkunMahasiswa(nim: String, password: String): Boolean {
        val hasilValidasi = CompletableDeferred<Boolean>()

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
                    hasilValidasi.complete(false)
                    return@addOnSuccessListener
                }

                if (mahasiswa.validatePassword(doc["password"] as String)) {
                    mahasiswa.setNama(doc["nama"] as String)
                    Otentikasi.setMahasiswa(mahasiswa)
                    hasilValidasi.complete(true)
                } else {
                    hasilValidasi.complete(false)
                }
            }
            .addOnFailureListener {e ->
                hasilValidasi.completeExceptionally(e)
            }

        return hasilValidasi.await()
    }
}