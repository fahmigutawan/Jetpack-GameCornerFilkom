package com.example.sigacorfilkom

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CompletableDeferred

class DaftarAkunAdmin {
    suspend fun validasiAkunAdmin(nip: String, password: String): Boolean {
        val hasilValidasi = CompletableDeferred<Boolean>()

        val admin = Admin(
            nip,
            password
        )

        FirebaseFirestore
            .getInstance()
            .collection("admin")
            .document(nip)
            .get()
            .addOnSuccessListener { doc ->
                if (doc.data == null) {
                    hasilValidasi.complete(false)
                    return@addOnSuccessListener
                }

                if (admin.validatePassword(doc["password"] as String)) {
                    Otentikasi.setAdmin(admin)
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