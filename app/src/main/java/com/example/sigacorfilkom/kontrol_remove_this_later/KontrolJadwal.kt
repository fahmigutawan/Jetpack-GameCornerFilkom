package com.example.sigacorfilkom.kontrol_remove_this_later

import android.util.Log
import com.example.sigacorfilkom.entity_remove_this_later.Hari
import com.example.sigacorfilkom.entity_remove_this_later.Jadwal
import com.example.sigacorfilkom.entity_remove_this_later.Perangkat
import com.example.sigacorfilkom.entity_remove_this_later.Reservasi
import com.example.sigacorfilkom.entity_remove_this_later.Sesi
import com.google.firebase.firestore.FirebaseFirestore
import com.google.type.DateTime
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import java.time.LocalTime
import java.util.Calendar
import java.util.Date
import java.util.UUID

class KontrolJadwal {
    companion object {
        private var jadwal = Jadwal()

        fun getHari() = jadwal.getHari()

        fun getPerangkat() = callbackFlow {
            FirebaseFirestore.getInstance()
                .collection("perangkat")
                .orderBy("nama")
                .addSnapshotListener { value, error ->
                    value?.let {
                        val res = it.documents.map { doc ->
                            Perangkat(
                                idPerangkat = doc["idPerangkat"] as String,
                                nama = doc["nama"] as String
                            )
                        }

                        jadwal.setPerangkat(res)

                        trySend(res)
                    }
                }

            awaitClose()
        }

        fun getSesi(
            tanggal: Int,
            bulan: Int,
            tahun: Int,
            idPerangkat: String
        ) = callbackFlow {
            val calendar = Calendar.getInstance()
            calendar.set(tahun, bulan - 1, tanggal)
            val timeMillis = calendar.timeInMillis
            val formattedPickedDate = "${tanggal}:${bulan}:${tahun}"


            FirebaseFirestore.getInstance()
                .collection("sesi")
                .addSnapshotListener { valueSesi, errorSesi ->
                    valueSesi?.let { valueSesiNotNull ->
                        val resTmp = ArrayList<Sesi>()
                        valueSesiNotNull.documents.forEach { docSesi ->
                            val sesiTmp = Sesi(
                                idPerangkat = idPerangkat,
                                nomorSesi = (docSesi["nomorSesi"] as Long).toInt(),
                                waktu = docSesi["waktu"] as String
                            )


                            if (LocalTime.now()
                                    .isAfter(sesiTmp.getStartTime()) && timeMillis <= System.currentTimeMillis()
                            ) {
                                //Get status booked karena jam
                                sesiTmp.setBooked(true)

                                resTmp.add(sesiTmp)

                                if (resTmp.size == valueSesiNotNull.documents.size) {
                                    trySend(resTmp.toList().sortedBy { it.getSesiNumber() })
                                }
                            } else {
                                //Get status booked karena reservasi
                                FirebaseFirestore.getInstance()
                                    .collection("reservasi")
                                    .whereEqualTo("pickedDay", formattedPickedDate )
                                    .whereEqualTo("idPerangkat", idPerangkat)
                                    .whereEqualTo("nomorSesi", docSesi["nomorSesi"])
                                    .addSnapshotListener { valueReservasi, errorReservasi ->
                                        valueReservasi?.let { valueReservasiNotNull ->
                                            sesiTmp.setBooked(valueReservasiNotNull.documents.isNotEmpty())

                                            resTmp.add(sesiTmp)

                                            if (resTmp.size == valueSesiNotNull.documents.size) {
                                                trySend(
                                                    resTmp.toList().sortedBy { it.getSesiNumber() })
                                            }
                                        }
                                    }
                            }
                        }
                    }
                }
            awaitClose()
        }

        fun getSesiByNomorSesi(
            nomorSesi: Int
        ) = callbackFlow {
            val firestore = FirebaseFirestore
                .getInstance()

            firestore
                .collection("sesi")
                .document(nomorSesi.toString())
                .addSnapshotListener { value, error ->
                    value?.let { doc ->
                        doc.data?.let {
                            val data = Sesi(
                                nomorSesi = (it["nomorSesi"] as Long).toInt(),
                                waktu = it["waktu"] as String
                            )

                            trySend(data)
                        }

                    }
                }

            awaitClose()
        }

        fun getPerangkatById(
            idPerangkat: String
        ) = callbackFlow {
            FirebaseFirestore
                .getInstance()
                .collection("perangkat")
                .whereEqualTo("idPerangkat", idPerangkat)
                .addSnapshotListener { value, error ->
                    value?.let {
                        val data = it.documents.map { doc ->
                            Perangkat(
                                idPerangkat = doc["idPerangkat"] as String,
                                nama = doc["nama"] as String
                            )
                        }

                        if (data.isNotEmpty()) {
                            trySend(data.first())
                        }
                    }
                }

            awaitClose()
        }
    }
}