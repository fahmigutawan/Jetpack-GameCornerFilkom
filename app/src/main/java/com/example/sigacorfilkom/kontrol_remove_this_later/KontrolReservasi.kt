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


class KontrolReservasi(
    kontrolOtentikasi: KontrolOtentikasi
) {
    private val kontrolOtentikasi:KontrolOtentikasi

    init {
        this.kontrolOtentikasi = kontrolOtentikasi
    }

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

        //Get array of tanggal untuk satu minggu
        val hariIni = Instant.ofEpochMilli(Calendar.getInstance().apply {
            set(tahun, bulan - 1, tanggal)
        }.timeInMillis)
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

        //Pertama, cek jumlah reservasi dalam 1 minggu dulu.
        firestore
            .collection("reservasi")
            .whereEqualTo("nimPeminjam", kontrolOtentikasi.getNimMahasiswa())
            .whereIn(
                "pickedDay",
                listLocalDate.map { "${it.dayOfMonth}:${it.monthValue}:${it.year}" }.toList()
            )
            .get()
            .addOnSuccessListener {
                if (it.documents.size >= 4) {
                    onFailed("Anda sudah mencapai batas maksimum peminjaman minggu ini")
                    return@addOnSuccessListener
                } else {
                    //Kedua, cek apakah satu hari sudah ada reservasi
                    firestore
                        .collection("reservasi")
                        .whereEqualTo("nimPeminjam", kontrolOtentikasi.getNimMahasiswa())
                        .whereEqualTo("pickedDay", pickedDay)
                        .get()
                        .addOnSuccessListener {
                            if (it.documents.size >= 1) {
                                onFailed("Anda hanya boleh melakukan reservasi 1 kali untuk satu hari, coba reservasi untuk hari lain")
                                return@addOnSuccessListener
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
                                        return@addOnSuccessListener
                                    }
                                    .addOnFailureListener {
                                        onFailed(it.message.toString())
                                        return@addOnFailureListener
                                    }
                            }
                        }
                        .addOnFailureListener {
                            onFailed(it.message.toString())
                            return@addOnFailureListener
                        }
                }
            }
            .addOnFailureListener {
                onFailed(it.message.toString())
                return@addOnFailureListener
            }
    }

    fun getReservasiTerbaruForAdmin() = callbackFlow {
        val nowMillis = System.currentTimeMillis()
        val instantNow = Instant.ofEpochMilli(nowMillis)
        val localDateNow = instantNow.atZone(ZoneId.systemDefault()).toLocalDate()

        val listDate = listOf(
            localDateNow,
            localDateNow.plusDays(1),
            localDateNow.plusDays(2)
        )

        FirebaseFirestore
            .getInstance()
            .collection("reservasi")
            .whereIn("pickedDay", listDate.map {
                "${it.dayOfMonth}:${it.monthValue}:${it.year}"
            })
            .orderBy("pickedDay", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener {
                trySend(
                    it.documents.map { doc ->
                        val format = DateTimeFormatter.ofPattern("dd:MM:yyyy")
                        val parsedDate = LocalDate.parse(doc["pickedDay"] as String, format)

                        Reservasi(
                            reservasiId = doc["idReservasi"] as String,
                            nimPeminjam = doc["nimPeminjam"] as String,
                            status = doc["status"] as String,
                            nomorSesi = (doc["nomorSesi"] as Long).toInt(),
                            idPerangkat = doc["idPerangkat"] as String,
                            tanggal = parsedDate.dayOfMonth,
                            bulan = parsedDate.monthValue,
                            tahun = parsedDate.year
                        )
                    }
                )
            }

        awaitClose()
    }

    fun getReservasiTerbaruForMahasiswa() = callbackFlow {
        val nowMillis = System.currentTimeMillis()
        val instantNow = Instant.ofEpochMilli(nowMillis)
        val localDateNow = instantNow.atZone(ZoneId.systemDefault()).toLocalDate()

        val listDate = listOf(
            localDateNow,
            localDateNow.plusDays(1),
            localDateNow.plusDays(2)
        )

        FirebaseFirestore
            .getInstance()
            .collection("reservasi")
            .whereEqualTo("nimPeminjam", kontrolOtentikasi.getNimMahasiswa())
            .whereIn("pickedDay", listDate.map {
                "${it.dayOfMonth}:${it.monthValue}:${it.year}"
            })
            .orderBy("pickedDay")
            .get()
            .addOnSuccessListener {
                trySend(
                    it.documents.map { doc ->
                        val format = DateTimeFormatter.ofPattern("dd:MM:yyyy")
                        val parsedDate = LocalDate.parse(doc["pickedDay"] as String, format)

                        Reservasi(
                            reservasiId = doc["idReservasi"] as String,
                            nimPeminjam = doc["nimPeminjam"] as String,
                            status = doc["status"] as String,
                            nomorSesi = (doc["nomorSesi"] as Long).toInt(),
                            idPerangkat = doc["idPerangkat"] as String,
                            tanggal = parsedDate.dayOfMonth,
                            bulan = parsedDate.monthValue,
                            tahun = parsedDate.year
                        )
                    }
                )
            }

        awaitClose()
    }

    fun updateStatusReservasi(
        idReservasi: String,
        status: String,
        onSuccess: () -> Unit,
        onFailed: (String) -> Unit
    ) {
        FirebaseFirestore
            .getInstance()
            .collection("reservasi")
            .document(idReservasi)
            .update(
                mapOf(
                    "status" to status
                )
            )
            .addOnSuccessListener {
                onSuccess()
                return@addOnSuccessListener
            }
            .addOnFailureListener {
                onFailed(it.message.toString())
                return@addOnFailureListener
            }
    }
}