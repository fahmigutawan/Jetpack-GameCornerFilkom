package com.example.sigacorfilkom.boundary_remove_this_later.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.sigacorfilkom.R
import com.example.sigacorfilkom.kontrol_remove_this_later.KontrolJadwal
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.ArrayList
import java.util.Calendar
import java.util.Date

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LayoutLogin(navController: NavController) {
    val scrWidth = LocalConfiguration.current

    Scaffold(
        containerColor = Color.White
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AsyncImage(
                    modifier = Modifier.padding(horizontal = 96.dp),
                    model = R.drawable.logo,
                    contentDescription = ""
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = {
                        navController.navigate("login_mahasiswa")
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xffFF9E3A),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Login Sebagai Mahasiswa")
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = {
                        navController.navigate("login_admin")
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xffFF9E3A),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Login Sebagai Admin")
                }
            }
        }
    }
}