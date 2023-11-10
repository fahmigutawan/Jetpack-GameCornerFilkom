package com.example.sigacorfilkom.kontrol_remove_this_later

import com.example.sigacorfilkom.entity_remove_this_later.Reservasi
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.UUID


class KontrolReservasi {
    companion object{
        fun buatReservasi(
            nimPeminjam:String,
            nomorSesi:String,
            idPerangkat:String,
            tanggal:Int,
            bulan:Int,
            tahun:Int
        ): Flow<Boolean> {
            val firestore = FirebaseFirestore.getInstance()
            val idReservasi = UUID.randomUUID().toString()
            val defaultStatus = "Menunggu"

            return callbackFlow {
                val reservasi = Reservasi(
                    reservasiId = idReservasi,
                    nimPeminjam = nimPeminjam,
                    status = defaultStatus,
                    nomorSesi = nomorSesi,
                    idPerangkat = idPerangkat,
                    tanggal = tanggal,
                    bulan = bulan,
                    tahun = tahun
                )

                firestore
                    .collection("reservasi")
                    .document(idReservasi)
                    .set(reservasi)
                    .addOnSuccessListener {
                        trySend(true)
                    }
                    .addOnFailureListener {
                        trySend(false)
                    }

                awaitClose()
            }
        }
    }
}