package com.example.sigacorfilkom

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CompletableDeferred

class DaftarPerangkat {
    suspend fun getDaftarPerangkat(): List<Perangkat> {
        val daftarPerangkat: MutableList<Perangkat> = mutableListOf()

        val callAsyncWaiter = CompletableDeferred<Unit>()
        FirebaseFirestore.getInstance()
            .collection("perangkat")
            .orderBy("nama")
            .get()
            .addOnSuccessListener{ value ->
                value?.let {
                    /**
                     * LOOP Data Perangkat
                     */
                    for (doc in it.documents) {
                        /**
                         * CALL   <<create(idPerangkat, nama)>>
                         * TUJUAN Perangkat
                         */
                        val perangkat = Perangkat(
                            idPerangkat = doc["idPerangkat"] as String,
                            nama = doc["nama"] as String
                        )
                        daftarPerangkat.add(perangkat)
                    }
                }
                callAsyncWaiter.complete(Unit)
            }
            .addOnFailureListener {e ->
                callAsyncWaiter.completeExceptionally(e)
            }
        callAsyncWaiter.await()

        return daftarPerangkat
    }
}