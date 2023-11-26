package com.example.sigacorfilkom.boundary_remove_this_later.login

import android.annotation.SuppressLint
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LayoutLogin(
    viewModel: HalamanLogin
) {
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
                        viewModel.navigasikeLoginMahasiswa()
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
                        viewModel.navigasiKeLoginAdmin()
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