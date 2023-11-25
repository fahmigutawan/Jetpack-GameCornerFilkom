package com.example.sigacorfilkom.kontrol_remove_this_later

import androidx.navigation.NavController

class KontrolNavigasi(
    navController: NavController
) {
    private val navController: NavController

    init {
        this.navController = navController
    }

    fun popHalaman() {
        navController.popBackStack()
    }

    fun navigasiKeHomeMahasiswa() {
        navController.navigate("home_mahasiswa") {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }

    fun navigasiKeHomeAdmin() {
        navController.navigate("home_admin") {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }

    fun navigasiKeLoginMahasiswa() = navController.navigate("login_mahasiswa")

    fun navigasiKeLoginAdmin() = navController.navigate("login_admin")

    fun navigasiKeRegister() = navController.navigate("register_mahasiswa")

    fun navigasiKeHalamanLogin() = navController.navigate("login") {
        popUpTo(navController.graph.id) { inclusive = true }
    }

    fun navigasiKePanduan() = navController.navigate("panduan_mahasiswa")

    fun navigasiKeJadwal() = navController.navigate("jadwal_mahasiswa")

    fun navigasiKeTutupJadwal() = navController.navigate("tutup_jadwal_admin")
}