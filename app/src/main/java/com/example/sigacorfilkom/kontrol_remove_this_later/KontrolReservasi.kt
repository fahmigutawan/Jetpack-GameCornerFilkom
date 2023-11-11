package com.example.sigacorfilkom.kontrol_remove_this_later

import android.util.Log
import com.example.sigacorfilkom.entity_remove_this_later.Reservasi
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firestore.v1.StructuredQuery.Order
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.ArrayList
import java.util.Calendar
import java.util.Date
import java.util.UUID


class KontrolReservasi {
    companion object {
        fun buatReservasi(
            nimPeminjam: String,
            nomorSesi: Int,
            idPerangkat: String,
            tanggal: Int,
            bulan: Int,
            tahun: Int,
            onSuccess: () -> Unit,
            onFailed: (String) -> Unit
        ) {
            val firestore = FirebaseFirestore.getInstance()
            val idReservasi = UUID.randomUUID().toString()
            val reservasi = Reservasi(
                reservasiId = idReservasi,
                nimPeminjam = nimPeminjam,
                nomorSesi = nomorSesi,
                idPerangkat = idPerangkat,
                tanggal = tanggal,
                bulan = bulan,
                tahun = tahun
            )
            val pickedDay = "${tanggal}:${bulan}:${tahun}"
            val nowInMillis = System.currentTimeMillis()
            val hariIni = Instant.ofEpochMilli(KontrolJadwal.getHari()[0].getTimeInMillis())
            val hariIniLocalDate = hariIni.atZone(ZoneId.systemDefault()).toLocalDate()
            val hariIniDiSatuMinggu = hariIniLocalDate.dayOfWeek.value
            val geserKiri = hariIniDiSatuMinggu - 1
            val geserKanan = 7 - hariIniDiSatuMinggu

            val listLocalDate = ArrayList<LocalDate>()

            if (geserKiri > 0) {
                for (i in geserKiri downTo 1) {
                    listLocalDate.add(hariIniLocalDate.minusDays(i.toLong()))
                }
            }

            listLocalDate.add(hariIniLocalDate)

            if (geserKanan > 0) {
                for (i in 1..geserKanan) {
                    listLocalDate.add(hariIniLocalDate.plusDays(i.toLong()))
                }
            }

            firestore
                .collection("reservasi")
                .whereEqualTo("nimPeminjam", KontrolOtentikasi.getNimMahasiswa())
                .whereIn("pickedDay", listLocalDate.map { "${it.dayOfMonth}:${it.monthValue}:${it.year}" }.toList())
                .addSnapshotListener { value, error ->
                    if (error != null) {
                        onFailed(error.message.toString())
                        return@addSnapshotListener
                    }

                    value?.let {
                        if (it.documents.size >= 4) {
                            onFailed("Anda sudah mencapai batas maksimum peminjaman minggu ini")
                            return@addSnapshotListener
                        } else {
                            firestore
                                .collection("reservasi")
                                .document(idReservasi)
                                .set(
                                    mapOf(
                                        "idReservasi" to reservasi.getReservasiId(),
                                        "nimPeminjam" to reservasi.getNimPeminjam(),
                                        "status" to reservasi.getDefaultStatus(),
                                        "nomorSesi" to reservasi.getNomorSesi(),
                                        "idPerangkat" to reservasi.getIdPerangkat(),
                                        "timeMillis" to nowInMillis,
                                        "pickedDay" to pickedDay
                                    )
                                )
                                .addOnSuccessListener {
                                    onSuccess()
                                }
                                .addOnFailureListener {
                                    onFailed(it.message.toString())
                                }
                        }
                    }
                }
        }

        fun getReservasiTerbaru() = callbackFlow {
            val nowMillis = System.currentTimeMillis()

            FirebaseFirestore
                .getInstance()
                .collection("reservasi")
                .whereGreaterThanOrEqualTo("timeMillis", nowMillis)
                .orderBy("timeMillis", Query.Direction.DESCENDING)
                .addSnapshotListener { value, error ->
                    value?.let {
                        trySend(
                            it.documents.map { doc ->
                                val timeMillis = doc["timeMillis"] as Long
                                val instant = Instant.ofEpochMilli(timeMillis)
                                val localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate()
                                Reservasi(
                                    reservasiId = doc["idReservasi"] as String,
                                    nimPeminjam = doc["nimPeminjam"] as String,
                                    status = doc["status"] as String,
                                    nomorSesi = (doc["nomorSesi"] as Long).toInt(),
                                    idPerangkat = doc["idPerangkat"] as String,
                                    tanggal = localDate.dayOfMonth,
                                    bulan = localDate.monthValue,
                                    tahun = localDate.year
                                )
                            }
                        )
                    }
                }

            awaitClose()
        }
    }
}