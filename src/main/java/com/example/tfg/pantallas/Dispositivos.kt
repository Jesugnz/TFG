package com.example.tfg.pantallas

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.tfg.R
import com.example.tfg.componentes.NavegacionInferior
import com.example.tfg.modelos.ItemsNav
import com.example.tfg.navegacion.NavegacionApp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaDispositivo(navController: NavHostController) {
    Scaffold (bottomBar = { NavegacionInferior(navController) }){
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Encabezado_D()
                Spacer(modifier = Modifier.padding(25.dp))
                Contenido_D()
            }
        }
    }
}

@Composable
fun Encabezado_D() {
    Text(text = "Sus dispositivos")
}

@Composable
fun Contenido_D() {
    Card(border = BorderStroke(3.dp, Color.Blue), modifier = Modifier
        .width(250.dp).height(90.dp).padding(15.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically){
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "logo", modifier = Modifier.size(75.dp, 75.dp)
            )
            Text(text = "Aire acondicionado")
        }
    }
}

@Composable
fun Botones_D(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(
                onClick = { navController.navigate(route = ItemsNav.Inicio.ruta) },
                modifier = Modifier
                    .size(85.dp, 35.dp)
                    .align(Alignment.Bottom),
                shape = RectangleShape
            ) {
                Text(text = "Inicio")
            }
            Button(
                onClick = { }, modifier = Modifier
                    .size(85.dp, 35.dp)
                    .align(Alignment.Bottom), shape = RectangleShape
            ) {
                Text(text = "Dispositivos")
            }
            Button(
                onClick = { navController.navigate(route = ItemsNav.Novedades.ruta) },
                modifier = Modifier
                    .size(85.dp, 35.dp)
                    .align(Alignment.Bottom),
                shape = RectangleShape
            ) {
                Text(text = "Novedades")
            }
            Button(
                onClick = { navController.navigate(route = ItemsNav.Cuenta.ruta) },
                modifier = Modifier
                    .size(85.dp, 35.dp)
                    .align(Alignment.Bottom),
                shape = RectangleShape
            ) {
                Text(text = "Cuenta")
            }
        }
    }
}