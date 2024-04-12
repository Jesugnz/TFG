package com.example.tfg.pantallas

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.tfg.R
import com.example.tfg.modelos.ItemsNav
import com.example.tfg.navegacion.PantallaNav

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaAyuda(navController: NavHostController) {
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Spacer(modifier = Modifier.padding(10.dp))
            Atras(
                Modifier
                    .align(Alignment.TopStart)
                    .size(50.dp)
                    .padding(10.dp), navController
            )
            Spacer(modifier = Modifier.padding(30.dp))
            Contenido_H()
        }
    }
}

@Composable
fun Atras(modificador: Modifier, navController: NavHostController) {
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = "Cerrar app",
        modifier = modificador.clickable { navController.navigate(route = PantallaNav.PantallaRegistro.name) },
        tint = Color.Black
    )
}

@Composable
fun Contenido_H() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PrimeraBox()
        SegundaBox()
        Spacer(modifier = Modifier.padding(15.dp))
        TerceraBox()
    }
}

@Composable
fun PrimeraBox() {
    Box {
        Column{
            Spacer(modifier = Modifier.padding(22.dp))
            Row {
                Spacer(modifier = Modifier.padding(13.dp))
                Text(
                    text = "El horario de asistencia es el siguiente: ",
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            }
            Row {
                Spacer(modifier = Modifier.padding(13.dp))
                Text(
                    text = "Lunes a Viernes -> 8:30 - 20:30",
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            }
            Row{
                Spacer(modifier = Modifier.padding(20.dp))
                Text(
                    text = "Sábados y Domingos -> 9:30 - 14:00",
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun SegundaBox() {
    Box(contentAlignment = Alignment.Center) {
        Column {
            Spacer(modifier = Modifier.padding(22.dp))
            Row {
                Spacer(modifier = Modifier.padding(13.dp))
                Text(
                    text = "Formas de contacto: ",
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            }
            Row {
                Spacer(modifier = Modifier.padding(13.dp))
                Text(text = "Tlf", textAlign = TextAlign.Center, color = Color.Black)
            }
            Row {
                Spacer(modifier = Modifier.padding(13.dp))
                Text(text = "Email", textAlign = TextAlign.Center, color = Color.Black)
            }
            Row {
                Spacer(modifier = Modifier.padding(13.dp))
                Text(text = "Oficinas", textAlign = TextAlign.Center, color = Color.Black)
            }
        }
    }
}

@Composable
fun TerceraBox() {
    Image(
        painter = painterResource(id = R.drawable.domoearth),
        contentDescription = "Mapa",
        modifier = Modifier
            .size(300.dp, 250.dp)
            .clickable {})
}