package com.example.sigacorfilkom.kontrol_remove_this_later

import com.example.sigacorfilkom.entity_remove_this_later.Hari
import com.example.sigacorfilkom.entity_remove_this_later.Jadwal
import com.example.sigacorfilkom.entity_remove_this_later.Perangkat
import com.example.sigacorfilkom.entity_remove_this_later.Reservasi
import com.example.sigacorfilkom.entity_remove_this_later.Sesi
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.util.Calendar
import java.util.Date
import java.util.UUID


class KontrolJadwal {
    private var jadwal = Jadwal()
    private val firestore = FirebaseFirestore.getInstance()

    fun getHari() = jadwal.getHari()

    fun getPerangkat() = callbackFlow {
        firestore
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
    ) = callbackFlow{
        val calendar = Calendar.getInstance()
        calendar.set(tahun, bulan, tanggal)
        val timeMillis = calendar.timeInMillis

        firestore
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

                        firestore
                            .collection("reservasi")
                            .whereEqualTo("tanggalMillis", timeMillis)
                            .whereEqualTo("idPerangkat", idPerangkat)
                            .whereEqualTo("nomorSesi", docSesi["nomorSesi"])
                            .addSnapshotListener { valueReservasi, errorReservasi ->
                                valueReservasi?.let { valueReservasiNotNull ->
                                    sesiTmp.setBooked(valueReservasiNotNull.documents.isNotEmpty())

                                    resTmp.add(sesiTmp)

                                    if(resTmp.size == valueSesiNotNull.documents.size){
                                        trySend(resTmp.toList().sortedBy { it.getSesiNumber() })
                                    }
                                }
                            }
                    }
                }
            }
        awaitClose()
    }
}