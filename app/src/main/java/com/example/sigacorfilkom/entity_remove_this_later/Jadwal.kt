package com.example.sigacorfilkom.entity_remove_this_later

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CompletableDeferred
import java.time.LocalDate

class Jadwal {
    private var perangkat: List<Perangkat> = listOf()
    private var sesi: List<Sesi> = listOf()

    suspend fun getDaftarHari(): List<Hari> {
        val daftarHari: MutableList<Hari> = mutableListOf()

        for (i in 0..2) {
            var isDitutup = false
            var alasanDitutup = ""

            val localDate = LocalDate.now().plusDays(i.toLong())

            when (localDate.dayOfWeek.value) {
                6, 7 -> {
                    isDitutup = true
                    alasanDitutup = "Hari Sabtu dan Minggu Tutup"
                }

                else -> {
                    val waiter = CompletableDeferred<Unit>()
                    FirebaseFirestore
                        .getInstance()
                        .collection("jadwal_tutup")
                        .document("${localDate.dayOfMonth}:${localDate.monthValue}:${localDate.year}")
                        .get()
                        .addOnSuccessListener {
                            if (it.data == null) {
                                isDitutup = false
                            } else {
                                isDitutup = true
                                alasanDitutup = it["alasan"] as String
                            }
                            waiter.complete(Unit)
                        }
                        .addOnFailureListener {
                            waiter.complete(Unit)
                        }
                    waiter.await()
                }
            }

            val hari = Hari(
                tanggal = localDate.dayOfMonth,
                bulan = localDate.month.value,
                tahun = localDate.year,
                isDitutup = isDitutup,
                alasanDitutup = alasanDitutup
            )
            daftarHari.add(hari)
        }

        return daftarHari
    }

    fun getPerangkat() = perangkat

    fun getPerangkatById(idPerangkat: String): Perangkat? = perangkat.findLast {
        it.getIdPerangkat() == idPerangkat
    }

    fun setPerangkat(value: List<Perangkat>) {
        perangkat = value
    }

    fun getSesi() = sesi

    fun setSesi(value: List<Sesi>) {
        sesi = value
    }
}