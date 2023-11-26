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
         * TUJUAN (E) Jadwal
         */
        jadwal = Jadwal()

        /**
         * CALL   getDaftarHari
         * TUJUAN (E) Jadwal
         * RETURN daftar hari
         */
        val daftarHari = jadwal.getDaftarHari()

        /**
         * CALL   <<create>>
         * TUJUAN (E) DaftarPerangkat
         */
        val daftarPerangkatEntity = DaftarPerangkat()

        /**
         * CALL   getDaftarPerangkat
         * TUJUAN (E) DaftarPerangkat
         * RETURN daftar perangkat
         */
        val daftarPerangkat = daftarPerangkatEntity.getDaftarPerangkat()

        /**
         * CALL   tampilkan
         * TUJUAN (B) HalamanJadwal
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
        /**
         *  CALL   getDaftarSesi
         *  TUJUAN (E) Jadwal
         *  RETURN daftar sesi
         */
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

    suspend fun tutupJadwal(
        tanggal: Int,
        bulan: Int,
        tahun: Int,
        alasan: String,
        onSuccess: () -> Unit,
        onFailed: (String) -> Unit
    ) {
        /**
         *  CALL   <<create>>
         *  TUJUAN (E) Hari
         */
        val hari = Hari(tanggal, bulan, tahun, false, "")

        /**
         *  CALL   <<create>>
         *  TUJUAN (E) Jadwal
         */
        val jadwal = Jadwal()

        /**
         *  CALL   tutupJadwal()
         *  TUJUAN (E) Jadwal
         *  RETURN boolean
         */
        val hasil = jadwal.tutupJadwal(hari, alasan)

        /**
         *  ALT true
         */
        if (hasil) {
            /**
             *  CALL tampilkan sukses
             */
            onSuccess()
        }
        /**
         *  ALT false
         */
        else {
            /**
             *  CALL tampilkan gagal
             */
            onFailed("Gagal menutup jadwal")
        }
    }
}