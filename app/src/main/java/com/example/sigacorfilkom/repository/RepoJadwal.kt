package com.example.sigacorfilkom.repository

import com.example.sigacorfilkom.model.Jadwal
import com.example.sigacorfilkom.model.Perangkat
import com.example.sigacorfilkom.model.Sesi
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import java.time.Instant
import java.time.ZoneId
import java.util.Calendar

class RepoJadwal {
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
                        val sesi = Sesi(
                            idPerangkat = idPerangkat,
                            nomorSesi = (docSesi["nomorSesi"] as Long).toInt(),
                            waktu = docSesi["waktu"] as String
                        )

                        //Get status booked karena jam
                        sesi.checkBookedKarenaJam(timeMillis)

                        //Get status booked karena reservasi
                        FirebaseFirestore.getInstance()
                            .collection("reservasi")
                            .whereEqualTo("pickedDay", formattedPickedDate)
                            .whereEqualTo("idPerangkat", idPerangkat)
                            .whereEqualTo("nomorSesi", docSesi["nomorSesi"])
                            .addSnapshotListener { valueReservasi, errorReservasi ->
                                valueReservasi?.let { valueReservasiNotNull ->
                                    sesi.setBooked(valueReservasiNotNull.documents.isNotEmpty())
                                    resTmp.add(sesi)

                                    if (resTmp.size == valueSesiNotNull.documents.size) {
                                        jadwal.setSesi(
                                            resTmp.toList().sortedBy { it.getSesiNumber() })
                                        trySend(
                                            resTmp.toList().sortedBy { it.getSesiNumber() })
                                    }
                                }
                            }
                    }
                }
            }
        awaitClose()
    }

    fun loadHari() {
        jadwal.getHari().forEach {
            it.reloadHari()
        }
    }

    fun tutupJadwal(
        dateMillis: Long,
        alasan: String,
        onSuccess: () -> Unit,
        onFailed: (String) -> Unit
    ) {
        val instant = Instant.ofEpochMilli(dateMillis)
        val localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate()
        val pickedDay = "${localDate.dayOfMonth}:${localDate.monthValue}:${localDate.year}"

        FirebaseFirestore
            .getInstance()
            .collection("jadwal_tutup")
            .document(pickedDay)
            .set(
                mapOf(
                    "waktu" to pickedDay,
                    "alasan" to alasan
                )
            ).addOnSuccessListener {
                onSuccess()
                return@addOnSuccessListener
            }
            .addOnFailureListener {
                onFailed(it.message.toString())
                return@addOnFailureListener
            }
    }
}