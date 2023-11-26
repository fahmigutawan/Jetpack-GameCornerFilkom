package com.example.sigacorfilkom

import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import java.time.Instant
import java.time.ZoneId

class KontrolJadwal(navigasi: NavController, aktivitas: SistemGameCorner) {
    private lateinit var jadwal: Jadwal
    private val navigasi: NavController
    private val aktivitas: SistemGameCorner

    init {
        this.navigasi = navigasi
        this.aktivitas = aktivitas
    }

    suspend fun tampilkanHalamanJadwal() {
        /**
         * CALL   <<create>>
         * TUJUAN Jadwal
         */
        jadwal = Jadwal()

        /**
         * CALL   getDaftarHari
         * TUJUAN Jadwal
         * RETURN daftar hari
         */
        val daftarHari = jadwal.getDaftarHari()

        /**
         * CALL   <<create>>
         * TUJUAN DaftarPerangkat
         */
        val daftarPerangkatEntity = DaftarPerangkat()

        /**
         * CALL   getDaftarPerangkat
         * TUJUAN DaftarPerangkat
         * RETURN daftar perangkat
         */
        val daftarPerangkat = daftarPerangkatEntity.getDaftarPerangkat()

        /**
         * CALL   tampilkan
         * TUJUAN HalamanJadwal
         */
        val halamanJadwal = aktivitas.getHalamanJadwal()
        halamanJadwal.setDaftarHari(daftarHari)
        halamanJadwal.setDaftarPerangkat(daftarPerangkat)
        navigasi.navigate("jadwal_mahasiswa")
    }

    suspend fun getDaftarSesi(
        tanggal: Int,
        bulan: Int,
        tahun: Int,
        idPerangkat: String
    ): List<Sesi> {
        return jadwal.getDaftarSesi(tanggal, bulan, tahun, idPerangkat)
    }

//    fun loadHari() {
//        jadwal.getDaftarHari().forEach {
//            it.reloadHari()
//        }
//    }

    fun tampilkanHalamanTutupJadwal() {
        navigasi.navigate("tutup_jadwal_admin")
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