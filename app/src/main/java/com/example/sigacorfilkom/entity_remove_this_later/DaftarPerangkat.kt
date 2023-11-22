package com.example.sigacorfilkom.entity_remove_this_later

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CompletableDeferred

class DaftarPerangkat {
    suspend fun getDaftarPerangkat(): List<Perangkat> {
        val hasilDaftarPerangkat = CompletableDeferred<List<Perangkat>>()

        FirebaseFirestore.getInstance()
            .collection("perangkat")
            .orderBy("nama")
            .get()
            .addOnSuccessListener{ value ->
                /**
                 * Buat penampung daftar perangkat
                 */
                val daftarPerangkat: MutableList<Perangkat> = mutableListOf()

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

                        /**
                         * tambahkan perangkat ke daftar perangkat
                         */
                        daftarPerangkat.add(perangkat)
                    }
                }

                /**
                 * beri informasi proses asinkron get perangkat selesai
                 */
                hasilDaftarPerangkat.complete(daftarPerangkat)
            }
            .addOnFailureListener {e ->
                hasilDaftarPerangkat.completeExceptionally(e)
            }

        return hasilDaftarPerangkat.await()
    }
}