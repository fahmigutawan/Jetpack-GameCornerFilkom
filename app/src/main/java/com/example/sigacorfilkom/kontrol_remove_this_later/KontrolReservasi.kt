package com.example.sigacorfilkom.kontrol_remove_this_later

import com.example.sigacorfilkom.entity_remove_this_later.BukuReservasi
import com.example.sigacorfilkom.entity_remove_this_later.Reservasi
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter


class KontrolReservasi(
    kontrolOtentikasi: KontrolOtentikasi
) {
    private val kontrolOtentikasi: KontrolOtentikasi

    init {
        this.kontrolOtentikasi = kontrolOtentikasi
    }

    suspend fun buatReservasi(
        nimPeminjam: String,
        nomorSesi: Int,
        idPerangkat: String,
        tanggal: Int,
        bulan: Int,
        tahun: Int
    ): Boolean {
        /**
         *  CALL   <<create>>
         *  TUJUAN BukuReservasi
         */
        val bukuReservasi = BukuReservasi()

        /**
         *  CALL   getJumlahReservasiPekanIni(nimPeminjam)
         *  TUJUAN (E) BukuReservasi
         *  RETURN (int) jumlahReservasiPekanIni
         */
        val jumlahReservasiPekanIni = bukuReservasi.getJumlahReservasiPekanIni(nimPeminjam)
        if (jumlahReservasiPekanIni >= 4) {
            throw Exception("Anda sudah mencapai batas maksimum peminjaman minggu ini")
        }

        /**
         *  CALL   getJumlahReservasiHariIni(nimPeminjam)
         *  TUJUAN (E) BukuReservasi
         *  RETURN (int) jumlahReservasiHariIni
         */
        val jumlahReservasiHariIni = bukuReservasi.getJumlahReservasiHariIni(nimPeminjam)
        if (jumlahReservasiHariIni >= 1) {
            throw Exception("Anda hanya boleh melakukan reservasi 1 kali untuk satu hari, coba reservasi untuk hari lain")
        }

        /**
         *  CALL   simpanReservasi(nimPeminjam, nomorSesi, idPerangkat, tanggal, bulan, tahun)
         *  TUJUAN (E) BukuReservasi
         *  RETURN boolean
         */
        val hasilSimpan = bukuReservasi.simpanReservasi(
            nimPeminjam, nomorSesi, idPerangkat, tanggal, bulan, tahun
        )

        return hasilSimpan
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