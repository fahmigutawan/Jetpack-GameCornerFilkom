package com.example.sigacorfilkom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sigacorfilkom.boundary_remove_this_later.jadwal.LayoutJadwal
import com.example.sigacorfilkom.boundary_remove_this_later.login.LayoutLogin
import com.example.sigacorfilkom.boundary_remove_this_later.login_admin.LayoutLoginAdmin
import com.example.sigacorfilkom.boundary_remove_this_later.login_mahasiswa.LayoutLoginMahasiswa
import com.example.sigacorfilkom.boundary_remove_this_later.panduan.LayoutPanduan
import com.example.sigacorfilkom.boundary_remove_this_later.register_mahasiswa.LayoutRegisterMahasiswa
import com.example.sigacorfilkom.boundary_remove_this_later.utama_admin.LayoutUtamaAdmin
import com.example.sigacorfilkom.boundary_remove_this_later.utama_mahasiswa.LayoutUtamaMahasiswa
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

lateinit var _showSnackbar: (message: String) -> Unit
lateinit var _showSnackbarWithAction: (
    message: String,
    actionLabel: String,
    action: () -> Unit
) -> Unit


class AktivitasUtama : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val coroutineScope = rememberCoroutineScope()
            val snackbarHostState = SnackbarHostState()

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

                    composable("login_admin") {
                        LayoutLoginAdmin(navController = navController)
                    }

                    composable("home_admin"){
                        LayoutUtamaAdmin(navController = navController)
                    }

                    composable("tutup_jadwal_admin"){

                    }
                }
            }
        }
    }
}