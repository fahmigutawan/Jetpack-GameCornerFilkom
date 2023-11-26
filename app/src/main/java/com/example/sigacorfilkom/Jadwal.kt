package com.example.sigacorfilkom

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CompletableDeferred
import java.time.LocalDate
import java.util.Calendar

class Jadwal {
    private var perangkat: List<Perangkat> = listOf()
    private var sesi: List<Sesi> = listOf()

    suspend fun getDaftarHari(): List<Hari> {
        val daftarHari: MutableList<Hari> = mutableListOf()

        /**
         *  CALL hari = 0
         *  LOOP hari < 3
         *  CALL hari = hari+1
         */
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
                    val callAsyncWaiter = CompletableDeferred<Unit>()
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
                            callAsyncWaiter.complete(Unit)
                        }
                        .addOnFailureListener {
                            callAsyncWaiter.complete(Unit)
                        }
                    callAsyncWaiter.await()
                }
            }

            /**
             *  CALL   <<create>>
             *  TUJUAN (E) Hari
             */
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

    suspend fun getDaftarSesi(
        tanggal: Int,
        bulan: Int,
        tahun: Int,
        idPerangkat: String
    ): List<Sesi> {
        val calendar = Calendar.getInstance()
        calendar.set(tahun, bulan - 1, tanggal)
        val timeMillis = calendar.timeInMillis
        val formattedPickedDate = "${tanggal}:${bulan}:${tahun}"

        val daftarSesi = ArrayList<Sesi>()

        // get daftar sesi dari database
        var asyncCallWaiter = CompletableDeferred<Unit>()
        FirebaseFirestore.getInstance()
            .collection("sesi")
            .get()
            .addOnSuccessListener { valueSesi ->
                valueSesi?.let { valueSesiNotNull ->
                    /**
                     *  LOOP data sesi
                     */
                    valueSesiNotNull.documents.forEach { docSesi ->
                        /**
                         *  CALL   <<create>>
                         *  TUJUAN (E) Sesi
                         */
                        val sesi = Sesi(
                            idPerangkat = idPerangkat,
                            nomorSesi = (docSesi["nomorSesi"] as Long).toInt(),
                            waktu = docSesi["waktu"] as String
                        )

                        //Get status booked karena jam
                        sesi.checkBookedKarenaJam(timeMillis)

                        daftarSesi.add(sesi)
                    }
                }
                asyncCallWaiter.complete(Unit)
            }
            .addOnFailureListener { e ->
                asyncCallWaiter.completeExceptionally(e)
            }
        asyncCallWaiter.await()

        //Get status booked karena reservasi
        for (sesi in daftarSesi) {
            asyncCallWaiter = CompletableDeferred()

            FirebaseFirestore.getInstance()
                .collection("reservasi")
                .whereEqualTo("pickedDay", formattedPickedDate)
                .whereEqualTo("idPerangkat", idPerangkat)
                .whereEqualTo("nomorSesi", sesi.getSesiNumber())
                .get()
                .addOnSuccessListener { valueReservasi ->
                    valueReservasi?.let { valueReservasiNotNull ->
                        sesi.setBooked(valueReservasiNotNull.documents.isNotEmpty())
                    }
                    asyncCallWaiter.complete(Unit)
                }
                .addOnFailureListener { _ ->
                    asyncCallWaiter.complete(Unit)
                }

            asyncCallWaiter.await()
        }

        return daftarSesi.toList().sortedBy { it.getSesiNumber() }
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