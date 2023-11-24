package com.example.sigacorfilkom.entity_remove_this_later

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CompletableDeferred
import java.time.LocalDate
import java.util.UUID

class BukuReservasi {
    suspend fun simpanReservasi(
        nimPeminjam: String,
        nomorSesi: Int,
        idPerangkat: String,
        tanggal: Int,
        bulan: Int,
        tahun: Int
    ): Boolean {
        val hasil = CompletableDeferred<Boolean>()

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
                hasil.complete(true)
            }
            .addOnFailureListener {
                hasil.completeExceptionally(it)
            }

        return hasil.await()
    }

    // Get jumlah reservasi dalam 1 minggu.
    suspend fun getJumlahReservasiPekanIni(nimPeminjam: String): Int {
        val hasilJumlahReservasi = CompletableDeferred<Int>()

        //Get array of tanggal untuk satu minggu
        val hariIni = LocalDate.now()
        val hariIniDiSatuMinggu = hariIni.dayOfWeek.value

        val geserKiri = hariIniDiSatuMinggu - 1
        val geserKanan = 7 - hariIniDiSatuMinggu

        val listLocalDate = ArrayList<LocalDate>()

        if (geserKiri > 0) {
            for (i in geserKiri downTo 1) {
                listLocalDate.add(hariIni.minusDays(i.toLong()))
            }
        }

        listLocalDate.add(hariIni)

        if (geserKanan > 0) {
            for (i in 1..geserKanan) {
                listLocalDate.add(hariIni.plusDays(i.toLong()))
            }
        }

        FirebaseFirestore.getInstance()
            .collection("reservasi")
            .whereEqualTo("nimPeminjam", nimPeminjam)
            .whereIn(
                "pickedDay",
                listLocalDate.map { "${it.dayOfMonth}:${it.monthValue}:${it.year}" }.toList()
            )
            .get()
            .addOnSuccessListener {
                hasilJumlahReservasi.complete(it.documents.size)
            }
            .addOnFailureListener { e ->
                hasilJumlahReservasi.completeExceptionally(e)
            }

        return hasilJumlahReservasi.await()
    }

    // Get jumlah reservasi dalam satu hari
    suspend fun getJumlahReservasiHariIni(nimPeminjam: String): Int {
        val hasilJumlahReservasi = CompletableDeferred<Int>()

        val hariIni = LocalDate.now()

        FirebaseFirestore.getInstance()
            .collection("reservasi")
            .whereEqualTo("nimPeminjam", nimPeminjam)
            .whereEqualTo("pickedDay", "${hariIni.dayOfMonth}:${hariIni.monthValue}:${hariIni.year}")
            .get()
            .addOnSuccessListener {
                hasilJumlahReservasi.complete(it.documents.size)
            }
            .addOnFailureListener {
                hasilJumlahReservasi.completeExceptionally(it)
            }

        return hasilJumlahReservasi.await()
    }
}