package com.example.sigacorfilkom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sigacorfilkom.boundary_remove_this_later.jadwal.LayoutJadwal


class AktivitasUtama : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            Scaffold {
                NavHost(
                    modifier = Modifier.padding(bottom = it.calculateBottomPadding()),
                    navController = navController,
                    startDestination = "onboard"
                ){
                    composable("onboard"){
                        LayoutJadwal()
                    }
                }
            }
        }
    }
}