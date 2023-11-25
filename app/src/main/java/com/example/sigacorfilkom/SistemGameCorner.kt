package com.example.sigacorfilkom

import android.os.Bundle
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.lifecycleScope
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
import com.example.sigacorfilkom.boundary_remove_this_later.login_admin.HalamanLoginAdmin
import com.example.sigacorfilkom.boundary_remove_this_later.login_admin.LayoutLoginAdmin
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolLoginMahasiswa
import com.example.sigacorfilkom.boundary_remove_this_later.otentikasi.Otentikasi
import com.example.sigacorfilkom.boundary_remove_this_later.panduan.HalamanPanduan
import com.example.sigacorfilkom.boundary_remove_this_later.panduan.LayoutPanduan
import com.example.sigacorfilkom.boundary_remove_this_later.tutup_jadwal_admin.HalamanTutupJadwalAdmin
import com.example.sigacorfilkom.boundary_remove_this_later.tutup_jadwal_admin.LayoutTutupJadwalAdmin
import com.example.sigacorfilkom.boundary_remove_this_later.utama_admin.HalamanUtamaAdmin
import com.example.sigacorfilkom.boundary_remove_this_later.utama_admin.LayoutUtamaAdmin
import com.example.sigacorfilkom.boundary_remove_this_later.utama_mahasiswa.HalamanUtamaMahasiswa
import com.example.sigacorfilkom.boundary_remove_this_later.utama_mahasiswa.LayoutUtamaMahasiswa
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolJadwal
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolLoginAdmin
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolReservasi
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolUtamaAdmin
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolUtamaMahasiswa
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

lateinit var _showSnackbar: (message: String) -> Unit
lateinit var _showSnackbarWithAction: (
    message: String,
    actionLabel: String,
    action: () -> Unit
) -> Unit


class SistemGameCorner : ComponentActivity() {
    private lateinit var halamanJadwal: HalamanJadwal
    private lateinit var halamanHistoryMahasiswa: HalamanHistoryMahasiswa

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val coroutineScope = rememberCoroutineScope()
            val snackbarHostState = SnackbarHostState()
            val showBottomBar = remember {
                mutableStateOf(false)
            }
            val currentRoute = remember {
                mutableStateOf("")
            }

            /**
             * Create seluruh kontrol
             */
            val kontrolJadwal = KontrolJadwal(navController, this)
            val kontrolLoginMahasiswa = KontrolLoginMahasiswa(navController)
            val kontrolLoginAdmin = KontrolLoginAdmin(navController)
            val kontrolUtamaMahasiswa = KontrolUtamaMahasiswa(navController)
            val kontrolUtamaAdmin = KontrolUtamaAdmin(navController)
            val kontrolRegisterMahasiswa = KontrolRegisterMahasiswa(navController)
            val kontrolReservasi =
                KontrolReservasi(
                    navController,
                    kontrolRegisterMahasiswa = kontrolRegisterMahasiswa,
                    this
                )

            /**
             * Create seluruh halaman
             */
            val halamanHistoryMahasiswa by viewModels<HalamanHistoryMahasiswa>() {
                viewModelFactory {
                    initializer {
                        HalamanHistoryMahasiswa(
                            kontrolJadwal, kontrolReservasi
                        )
                    }
                }
            }
            this.halamanHistoryMahasiswa = halamanHistoryMahasiswa

            val halamanJadwal: HalamanJadwal by viewModels {
                viewModelFactory {
                    initializer {
                        HalamanJadwal(
                            kontrolJadwal, kontrolReservasi, kontrolRegisterMahasiswa
                        )
                    }
                }
            }
            this.halamanJadwal = halamanJadwal

            val halamanLogin: HalamanLogin by viewModels {
                viewModelFactory {
                    initializer {
                        HalamanLogin(kontrolLoginMahasiswa, kontrolLoginAdmin)
                    }
                }
            }
            val halamanLoginMahasiswa: HalamanLoginMahasiswa by viewModels {
                viewModelFactory {
                    initializer {
                        HalamanLoginMahasiswa(
                            kontrolLoginMahasiswa,
                            kontrolRegisterMahasiswa
                        )
                    }
                }
            }
            val halamanLoginAdmin: HalamanLoginAdmin by viewModels {
                viewModelFactory {
                    initializer {
                        HalamanLoginAdmin(
                            kontrolLoginAdmin
                        )
                    }
                }
            }
            val halamanPanduan: HalamanPanduan by viewModels {
                viewModelFactory {
                    initializer {
                        HalamanPanduan()
                    }
                }
            }
            val halamanRegisterMahasiswa: HalamanRegisterMahasiswa by viewModels {
                viewModelFactory {
                    initializer {
                        HalamanRegisterMahasiswa(
                            kontrolRegisterMahasiswa
                        )
                    }
                }
            }
            val halamanTutupJadwalAdmin: HalamanTutupJadwalAdmin by viewModels {
                viewModelFactory {
                    initializer {
                        HalamanTutupJadwalAdmin(
                            kontrolJadwal
                        )
                    }
                }
            }
            val halamanUtamaAdmin: HalamanUtamaAdmin by viewModels {
                viewModelFactory {
                    initializer {
                        HalamanUtamaAdmin(
                            kontrolJadwal, kontrolReservasi, kontrolUtamaAdmin
                        )
                    }
                }
            }
            val halamanUtamaMahasiswa: HalamanUtamaMahasiswa by viewModels {
                viewModelFactory {
                    initializer {
                        HalamanUtamaMahasiswa(
                            kontrolJadwal, kontrolUtamaMahasiswa
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
            _showSnackbar = {
                coroutineScope.launch(Dispatchers.IO) {
                    snackbarHostState.currentSnackbarData?.dismiss()
                    snackbarHostState.showSnackbar(it)
                }
            }
            _showSnackbarWithAction = { msg, label, action ->
                coroutineScope.launch(Dispatchers.IO) {
                    snackbarHostState.currentSnackbarData?.dismiss()
                    val snackbarData = snackbarHostState
                        .showSnackbar(
                            message = msg,
                            actionLabel = label
                        )

                    if (snackbarData == SnackbarResult.ActionPerformed) {
                        if (label == "Tutup") {
                            snackbarHostState.currentSnackbarData?.dismiss()
                        } else {
                            action()
                        }
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
                        if (Otentikasi.getAdmin() != null) {
                            BottomAppBar {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    IconButton(onClick = {
                                        navController.navigate("home_admin")
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
                                        kontrolJadwal.tampilkanHalamanTutupJadwal()
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
                                        lifecycleScope.launch {
                                            kontrolReservasi.tampilkanHalamanRiwayatReservasi()
                                        }
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
                        LayoutLoginMahasiswa(viewModel = halamanLoginMahasiswa)
                    }

                    composable("register_mahasiswa") {
                        LayoutRegisterMahasiswa(viewModel = halamanRegisterMahasiswa)
                    }

                    composable("home_mahasiswa") {
                        LayoutUtamaMahasiswa(navController, viewModel = halamanUtamaMahasiswa)
                    }

                    composable("jadwal_mahasiswa") {
                        LayoutJadwal(viewModel = halamanJadwal)
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
                        LayoutLoginAdmin(viewModel = halamanLoginAdmin)
                    }

                    composable("home_admin") {
                        LayoutUtamaAdmin(
                            navController,
                            viewModel = halamanUtamaAdmin
                        )
                    }

                    composable("tutup_jadwal_admin") {
                        LayoutTutupJadwalAdmin(viewModel = halamanTutupJadwalAdmin)
                    }
                }
            }
        }
    }

    fun getHalamanJadwal(): HalamanJadwal {
        return halamanJadwal
    }

    fun getHalamanHistoryMahasiswa(): HalamanHistoryMahasiswa {
        return halamanHistoryMahasiswa
    }
}