package com.example.sigacorfilkom.kontrol_remove_this_later

import androidx.navigation.NavController
import com.example.sigacorfilkom.AktivitasUtama
import com.example.sigacorfilkom.entity_remove_this_later.DaftarPerangkat
import com.example.sigacorfilkom.entity_remove_this_later.Jadwal
import com.example.sigacorfilkom.entity_remove_this_later.Sesi
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import java.time.Instant
import java.time.ZoneId
import java.util.Calendar

class KontrolJadwal(navigasi: NavController, aktivitas: AktivitasUtama) {
    private var jadwal = Jadwal()
    private val navigasi: NavController
    private val aktivitas: AktivitasUtama

    init {
        this.navigasi = navigasi
        this.aktivitas = aktivitas
    }

    suspend fun tampilkanHalamanJadwal() {
        /**
         * CALL   <<create>>
         * TUJUAN DaftarPerangkat
         */
        val daftarPerangkatEntity = DaftarPerangkat()
        /**
         * CALL   getDaftarPerangkat
         * TUJUAN DaftarPerangkat
         * TERIMA daftar perangkat
         */
        val daftarPerangkat = daftarPerangkatEntity.getDaftarPerangkat()

        /**
         * CALL   tampilkan
         * TUJUAN HalamanJadwal
         */
        val halamanJadwal = aktivitas.getHalamanJadwal()
        halamanJadwal.setDaftarPerangkat(daftarPerangkat)
        navigasi.navigate("jadwal_mahasiswa")
    }

    fun getHari() = jadwal.getHari()

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