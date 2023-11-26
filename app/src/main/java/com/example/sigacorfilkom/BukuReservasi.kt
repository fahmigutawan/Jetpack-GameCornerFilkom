package com.example.sigacorfilkom

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CompletableDeferred
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
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
            .whereEqualTo(
                "pickedDay",
                "${hariIni.dayOfMonth}:${hariIni.monthValue}:${hariIni.year}"
            )
            .get()
            .addOnSuccessListener {
                hasilJumlahReservasi.complete(it.documents.size)
            }
            .addOnFailureListener {
                hasilJumlahReservasi.completeExceptionally(it)
            }

        return hasilJumlahReservasi.await()
    }

    suspend fun getDaftarReservasiForMahasiswa(nimPeminjam: String): List<Reservasi> {
        val daftarReservasi: MutableList<Reservasi> = mutableListOf()

        val nowMillis = System.currentTimeMillis()
        val instantNow = Instant.ofEpochMilli(nowMillis)
        val localDateNow = instantNow.atZone(ZoneId.systemDefault()).toLocalDate()

        val listDate = listOf(
            localDateNow,
            localDateNow.plusDays(1),
            localDateNow.plusDays(2)
        )

        val asyncCallWaiter = CompletableDeferred<Unit>()
        FirebaseFirestore
            .getInstance()
            .collection("reservasi")
            .whereEqualTo("nimPeminjam", nimPeminjam)
            .whereIn("pickedDay", listDate.map {
                "${it.dayOfMonth}:${it.monthValue}:${it.year}"
            })
            .orderBy("pickedDay")
            .get()
            .addOnSuccessListener {
                /**
                 *  LOOP data reservasi
                 */
                it.documents.forEach { doc ->
                    val format = DateTimeFormatter.ofPattern("dd:MM:yyyy")
                    val parsedDate = LocalDate.parse(doc["pickedDay"] as String, format)

                    /**
                     *  CALL   <<create>>
                     *  TUJUAN (E) Reservasi
                     */
                    val reservasi = Reservasi(
                        reservasiId = doc["idReservasi"] as String,
                        nimPeminjam = doc["nimPeminjam"] as String,
                        status = doc["status"] as String,
                        nomorSesi = (doc["nomorSesi"] as Long).toInt(),
                        idPerangkat = doc["idPerangkat"] as String,
                        tanggal = parsedDate.dayOfMonth,
                        bulan = parsedDate.monthValue,
                        tahun = parsedDate.year
                    )

                    daftarReservasi.add(reservasi)
                }

                asyncCallWaiter.complete(Unit)
            }
            .addOnFailureListener {
                asyncCallWaiter.complete(Unit)
            }
        asyncCallWaiter.await()

        return daftarReservasi
    }

    suspend fun validasiReservasi(reservasi: Reservasi, isValid: Boolean): Boolean {
        var hasilValidasi = false

        val status = when(isValid) {
            true -> "Divalidasi"
            false -> "Gagal"
        }

        val asyncCallWaiter = CompletableDeferred<Unit>()
        FirebaseFirestore
            .getInstance()
            .collection("reservasi")
            .document(reservasi.getReservasiId())
            .update(
                mapOf(
                    "status" to status
                )
            )
            .addOnSuccessListener {
                hasilValidasi = true
                asyncCallWaiter.complete(Unit)
            }
            .addOnFailureListener {
                hasilValidasi = false
                asyncCallWaiter.complete(Unit)
            }
        asyncCallWaiter.await()

        return hasilValidasi
    }
}