package com.example.sigacorfilkom

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.sigacorfilkom.boundary_remove_this_later.history_mahasiswa.HalamanHistoryMahasiswa
import com.example.sigacorfilkom.boundary_remove_this_later.history_mahasiswa.LayoutHistoryMahasiswa
import com.example.sigacorfilkom.boundary_remove_this_later.jadwal.HalamanJadwal
import com.example.sigacorfilkom.boundary_remove_this_later.jadwal.LayoutJadwal
import com.example.sigacorfilkom.boundary_remove_this_later.login.HalamanLogin
import com.example.sigacorfilkom.boundary_remove_this_later.login.LayoutLogin
import com.example.sigacorfilkom.boundary_remove_this_later.login_admin.HalamanLoginAdmin
import com.example.sigacorfilkom.boundary_remove_this_later.login_admin.LayoutLoginAdmin
import com.example.sigacorfilkom.boundary_remove_this_later.login_mahasiswa.HalamanLoginMahasiswa
import com.example.sigacorfilkom.boundary_remove_this_later.login_mahasiswa.LayoutLoginMahasiswa
import com.example.sigacorfilkom.boundary_remove_this_later.panduan.HalamanPanduan
import com.example.sigacorfilkom.boundary_remove_this_later.panduan.LayoutPanduan
import com.example.sigacorfilkom.boundary_remove_this_later.register_mahasiswa.HalamanRegisterMahasiswa
import com.example.sigacorfilkom.boundary_remove_this_later.register_mahasiswa.LayoutRegisterMahasiswa
import com.example.sigacorfilkom.boundary_remove_this_later.tutup_jadwal_admin.HalamanTutupJadwalAdmin
import com.example.sigacorfilkom.boundary_remove_this_later.tutup_jadwal_admin.LayoutTutupJadwalAdmin
import com.example.sigacorfilkom.boundary_remove_this_later.utama_admin.HalamanUtamaAdmin
import com.example.sigacorfilkom.boundary_remove_this_later.utama_admin.LayoutUtamaAdmin
import com.example.sigacorfilkom.boundary_remove_this_later.utama_mahasiswa.HalamanUtamaMahasiswa
import com.example.sigacorfilkom.boundary_remove_this_later.utama_mahasiswa.LayoutUtamaMahasiswa
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolJadwal
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolNavigasi
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolOtentikasi
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolReservasi
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolSnackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class AktivitasUtama : ComponentActivity() {
    private lateinit var kontrolNavigasi: KontrolNavigasi
    private lateinit var kontrolSnackbar: KontrolSnackbar
    private lateinit var kontrolJadwal: KontrolJadwal
    private lateinit var kontrolOtentikasi: KontrolOtentikasi
    private lateinit var kontrolReservasi: KontrolReservasi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val snackbarHostState = SnackbarHostState()
            val coroutineScope = rememberCoroutineScope()

            //Init kontrol
            kontrolNavigasi = KontrolNavigasi(navController)
            kontrolSnackbar = KontrolSnackbar {
                coroutineScope.launch(Dispatchers.IO) {
                    snackbarHostState.currentSnackbarData?.dismiss()
                    snackbarHostState.showSnackbar(it)
                }
            }
            kontrolOtentikasi = KontrolOtentikasi(kontrolSnackbar)
            kontrolReservasi = KontrolReservasi(kontrolOtentikasi, kontrolSnackbar)
            kontrolJadwal = KontrolJadwal(kontrolSnackbar)

            //Other attrs
            val showBottomBar = remember {
                mutableStateOf(false)
            }
            val currentRoute = remember {
                mutableStateOf("")
            }
            val halamanHistoryMahasiswa: HalamanHistoryMahasiswa by viewModels() {
                viewModelFactory {
                    initializer {
                        HalamanHistoryMahasiswa(
                            kontrolJadwal, kontrolReservasi,kontrolNavigasi
                        )
                    }
                }
            }
            val halamanJadwal:HalamanJadwal by viewModels {
                viewModelFactory {
                    initializer {
                        HalamanJadwal(
                            kontrolJadwal, kontrolReservasi, kontrolOtentikasi,kontrolNavigasi
                        )
                    }
                }
            }
            val halamanLogin:HalamanLogin by viewModels {
                viewModelFactory {
                    initializer {
                        HalamanLogin(kontrolNavigasi)
                    }
                }
            }
            val halamanLoginMahasiswa:HalamanLoginMahasiswa by viewModels {
                viewModelFactory {
                    initializer {
                        HalamanLoginMahasiswa(
                            kontrolOtentikasi,kontrolNavigasi, kontrolSnackbar
                        )
                    }
                }
            }
            val halamanLoginAdmin:HalamanLoginAdmin by viewModels {
                viewModelFactory {
                    initializer {
                        HalamanLoginAdmin(
                            kontrolOtentikasi,kontrolNavigasi
                        )
                    }
                }
            }
            val halamanPanduan:HalamanPanduan by viewModels {
                viewModelFactory {
                    initializer {
                        HalamanPanduan(kontrolNavigasi)
                    }
                }
            }
            val halamanRegisterMahasiswa: HalamanRegisterMahasiswa  by viewModels {
                viewModelFactory {
                    initializer {
                        HalamanRegisterMahasiswa(
                            kontrolOtentikasi,kontrolNavigasi, kontrolSnackbar
                        )
                    }
                }
            }
            val halamanTutupJadwalAdmin: HalamanTutupJadwalAdmin by viewModels {
                viewModelFactory {
                    initializer {
                        HalamanTutupJadwalAdmin(
                            kontrolJadwal,kontrolNavigasi
                        )
                    }
                }
            }
            val halamanUtamaAdmin: HalamanUtamaAdmin by viewModels {
                viewModelFactory {
                    initializer {
                        HalamanUtamaAdmin(
                            kontrolJadwal, kontrolReservasi, kontrolOtentikasi,kontrolNavigasi
                        )
                    }
                }
            }
            val halamanUtamaMahasiswa:HalamanUtamaMahasiswa by viewModels {
                viewModelFactory {
                    initializer {
                        HalamanUtamaMahasiswa(
                            kontrolJadwal, kontrolOtentikasi,kontrolNavigasi
                        )
                    }
                }
            }

            navController.addOnDestinationChangedListener { _, dest, _ ->
                when (dest.route) {
                    "home_mahasiswa", "home_admin", "tutup_jadwal_admin", "history_mahasiswa" -> {
                        showBottomBar.value = true
                        currentRoute.value = dest.route ?: ""
                    }

                    else -> {
                        showBottomBar.value = false
                        currentRoute.value = ""
                    }
                }
            }

            Scaffold(
                snackbarHost = {
                    SnackbarHost(
                        hostState = snackbarHostState
                    ) {
                        Snackbar(snackbarData = it)
                    }
                },
                bottomBar = {
                    if (showBottomBar.value) {
                        if (kontrolOtentikasi.isAdmin()) {
                            BottomAppBar {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    IconButton(onClick = {
                                        kontrolNavigasi.navigasiKeHomeAdmin()
                                    }) {
                                        Icon(
                                            painter = rememberAsyncImagePainter(model = R.drawable.iconhome),
                                            tint = if (currentRoute.value == "home_admin") Color(
                                                0xffFF8B13
                                            ) else Color(0xffCBD5E1),
                                            contentDescription = ""
                                        )
                                    }

                                    IconButton(onClick = {
                                        kontrolNavigasi.navigasiKeTutupJadwal()
                                    }) {
                                        Icon(
                                            painter = rememberAsyncImagePainter(model = R.drawable.ic_tutup_admin),
                                            tint = if (currentRoute.value == "tutup_jadwal_admin") Color(
                                                0xffFF8B13
                                            ) else Color(0xffCBD5E1),
                                            contentDescription = ""
                                        )
                                    }
                                }
                            }
                        } else {
                            BottomAppBar {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    IconButton(onClick = {
                                        navController.navigate("home_mahasiswa")
                                    }) {
                                        Icon(
                                            painter = rememberAsyncImagePainter(model = R.drawable.iconhome),
                                            tint = if (currentRoute.value == "home_mahasiswa") Color(
                                                0xffFF8B13
                                            ) else Color(0xffCBD5E1),
                                            contentDescription = ""
                                        )
                                    }

                                    IconButton(onClick = {
                                        navController.navigate("history_mahasiswa")
                                    }) {
                                        Icon(
                                            painter = rememberAsyncImagePainter(model = R.drawable.ic_history_mhs),
                                            tint = if (currentRoute.value == "history_mahasiswa") Color(
                                                0xffFF8B13
                                            ) else Color(0xffCBD5E1),
                                            contentDescription = ""
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            ) {
                NavHost(
                    modifier = Modifier.padding(bottom = it.calculateBottomPadding()),
                    navController = navController,
                    startDestination = "login"
                ) {
                    composable("login") {
                        LayoutLogin(viewModel = halamanLogin)
                    }

                    composable("login_mahasiswa") {
                        LayoutLoginMahasiswa(viewModel=halamanLoginMahasiswa)
                    }

                    composable("register_mahasiswa") {
                        LayoutRegisterMahasiswa(viewModel=halamanRegisterMahasiswa)
                    }

                    composable("home_mahasiswa") {
                        LayoutUtamaMahasiswa(viewModel=halamanUtamaMahasiswa)
                    }

                    composable("jadwal_mahasiswa") {
                        LayoutJadwal(viewModel=halamanJadwal)
                    }

                    composable("panduan_mahasiswa") {
                        LayoutPanduan(viewModel = halamanPanduan)
                    }

                    composable("history_mahasiswa") {
                        LayoutHistoryMahasiswa(
                            viewModel = halamanHistoryMahasiswa
                        )
                    }

                    composable("login_admin") {
                        LayoutLoginAdmin(
                            viewModel= halamanLoginAdmin
                        )
                    }

                    composable("home_admin") {
                        LayoutUtamaAdmin(
                            viewModel=halamanUtamaAdmin
                        )
                    }

                    composable("tutup_jadwal_admin") {
                        LayoutTutupJadwalAdmin(
                            viewModel=halamanTutupJadwalAdmin
                        )
                    }
                }
            }
        }
    }
}