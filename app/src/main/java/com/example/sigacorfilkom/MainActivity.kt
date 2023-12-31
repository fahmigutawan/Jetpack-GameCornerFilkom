package com.example.sigacorfilkom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.sigacorfilkom.presentation.history_mahasiswa.LayoutHistoryMahasiswa
import com.example.sigacorfilkom.presentation.jadwal.LayoutJadwal
import com.example.sigacorfilkom.presentation.login.LayoutLogin
import com.example.sigacorfilkom.presentation.login_admin.LayoutLoginAdmin
import com.example.sigacorfilkom.presentation.login_mahasiswa.LayoutLoginMahasiswa
import com.example.sigacorfilkom.presentation.panduan.LayoutPanduan
import com.example.sigacorfilkom.presentation.register_mahasiswa.LayoutRegisterMahasiswa
import com.example.sigacorfilkom.presentation.tutup_jadwal_admin.LayoutTutupJadwalAdmin
import com.example.sigacorfilkom.presentation.utama_admin.LayoutUtamaAdmin
import com.example.sigacorfilkom.presentation.utama_mahasiswa.LayoutUtamaMahasiswa
import com.example.sigacorfilkom.repository.RepoJadwal
import com.example.sigacorfilkom.repository.RepoOtentikasi
import com.example.sigacorfilkom.repository.RepoReservasi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

lateinit var _showSnackbar: (message: String) -> Unit
lateinit var _showSnackbarWithAction: (
    message: String,
    actionLabel: String,
    action: () -> Unit
) -> Unit
lateinit var repoJadwal: RepoJadwal
lateinit var repoOtentikasi: RepoOtentikasi
lateinit var repoReservasi: RepoReservasi


class AktivitasUtama : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repoJadwal = RepoJadwal()
        repoOtentikasi = RepoOtentikasi()
        repoReservasi = RepoReservasi()

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
                        if (repoOtentikasi.isAdmin()) {
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
                                        navController.navigate("tutup_jadwal_admin")
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
                        LayoutLogin(navController = navController)
                    }

                    composable("login_mahasiswa") {
                        LayoutLoginMahasiswa(navController = navController)
                    }

                    composable("register_mahasiswa") {
                        LayoutRegisterMahasiswa(navController = navController)
                    }

                    composable("home_mahasiswa") {
                        LayoutUtamaMahasiswa(navController = navController)
                    }

                    composable("jadwal_mahasiswa") {
                        LayoutJadwal(navController = navController)
                    }

                    composable("panduan_mahasiswa") {
                        LayoutPanduan(navController = navController)
                    }

                    composable("history_mahasiswa") {
                        LayoutHistoryMahasiswa(navController = navController)
                    }

                    composable("login_admin") {
                        LayoutLoginAdmin(navController = navController)
                    }

                    composable("home_admin") {
                        LayoutUtamaAdmin(navController = navController)
                    }

                    composable("tutup_jadwal_admin") {
                        LayoutTutupJadwalAdmin(navController = navController)
                    }
                }
            }
        }
    }
}