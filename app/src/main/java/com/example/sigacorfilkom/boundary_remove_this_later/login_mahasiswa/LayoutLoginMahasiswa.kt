package com.example.sigacorfilkom.boundary_remove_this_later.login_mahasiswa

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.sigacorfilkom.R
import com.example.sigacorfilkom.SnackbarHandler

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LayoutLoginMahasiswa(navController: NavController) {
    val viewModel = viewModel<HalamanLoginMahasiswa>()

    Scaffold(
        containerColor = Color.White
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), contentAlignment = Alignment.Center) {
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

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.getNim().value,
                    onValueChange = { viewModel.setNim(it) },
                    placeholder = {
                        Text(text = "NIM")
                    }
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.getPassword().value,
                    onValueChange = { viewModel.setPassword(it) },
                    placeholder = {
                        Text(text = "Password")
                    },
                    visualTransformation = PasswordVisualTransformation()
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Belum punya akun?")
                    TextButton(onClick = {
                        navController.navigate("register_mahasiswa")
                    }) {
                        Text(text = "Registrasi")
                    }
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        viewModel.login(
                            onSuccess = {
                                navController.navigate("home_mahasiswa"){
//                                    popUpTo(navController.graph.id){
//                                        inclusive = true
//                                    }
                                }
                            },
                            onFailed = {
                                SnackbarHandler.showSnackbar(it)
                            }
                        )
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xffFF9E3A),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Login")
                }
            }
        }
    }
}