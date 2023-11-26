package com.example.sigacorfilkom

import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter


class KontrolReservasi(
    navigasi: NavController,
    kontrolRegisterMahasiswa: KontrolRegisterMahasiswa,
    aktivitas: SistemGameCorner
) {
    private val kontrolRegisterMahasiswa: KontrolRegisterMahasiswa
    private val navigasi: NavController
    private val aktivitas: SistemGameCorner

    private lateinit var bukuReservasi: BukuReservasi

    init {
        this.kontrolRegisterMahasiswa = kontrolRegisterMahasiswa
        this.navigasi = navigasi
        this.aktivitas = aktivitas
    }

    suspend fun buatReservasi(
        nomorSesi: Int,
        idPerangkat: String,
        tanggal: Int,
        bulan: Int,
        tahun: Int
    ): Boolean {
        val nimPeminjam = Otentikasi.getMahasiswa()!!.getNim()

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

        bukuReservasi = BukuReservasi()

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

    suspend fun validasiReservasi(
        reservasi: Reservasi,
        isValid: Boolean,
        onSuccess: () -> Unit,
        onFailed: (String) -> Unit
    ) {
        /**
         *  CALL   validasiReservasi()
         *  TUJUAN (E) BukuReservasi
         *  RETURN boolean
         */
        val hasilValidasi = bukuReservasi.validasiReservasi(reservasi, isValid)

        /**
         *  ALT true
         */
        if (hasilValidasi) {
            /**
             *  CALL   tampilkan sukses
             *  TUJUAN (B) HalamanUtamaAdmin
             */
            onSuccess()
        } else {
            /**
             *  CALL   tampilkan error
             *  TUJUAN (B) HalamanUtamaAdmin
             */
            onFailed("Gagal melakukan validasi")
        }
    }

    suspend fun tampilkanHalamanRiwayatReservasiForMahasiswa() {
        val halamanHistoryMahasiswa = aktivitas.getHalamanHistoryMahasiswa()
        val nimMahasiswa = Otentikasi.getMahasiswa()!!.getNim()

        /**
         *  CALL   <<create>>
         *  TUJUAN (E) DaftarPerangkat
         */
        val daftarPerangkatEntity = DaftarPerangkat()

        /**
         *  CALL   getDaftarPerangkat()
         *  TUJUAN (E) DaftarPerangkat
         *  RETURN daftar perangkat
         */
        val daftarPerangkat = daftarPerangkatEntity.getDaftarPerangkat()


        /**
         *  CALL   <<create>>
         *  Tujuan (E) BukuReservasi
         */
        val bukuReservasi = BukuReservasi()

        /**
         *  CALL   getDaftarReservasiForMahasiswa(nimMahasiswa)
         *  TUJUAN (E) BukuReservasi
         *  RETURN daftar reservasi
         */
        val daftarReservasi = bukuReservasi.getDaftarReservasiForMahasiswa(nimMahasiswa)

        /**
         *  CALL   tampilkan
         *  TUJUAN HalamanHistoryMahasiswa
         */
        halamanHistoryMahasiswa.setDaftarNamaPerangkat(daftarPerangkat)
        halamanHistoryMahasiswa.setDaftarReservasi(daftarReservasi)
        navigasi.navigate("history_mahasiswa")
    }
}