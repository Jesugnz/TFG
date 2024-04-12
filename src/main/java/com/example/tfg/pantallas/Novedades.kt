package com.example.tfg.pantallas

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.tfg.componentes.NavegacionInferior
import com.example.tfg.modelos.ItemsNav
import com.example.tfg.navegacion.NavegacionApp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaNovedades(navController: NavHostController) {
    Scaffold(bottomBar = { NavegacionInferior(navController) }) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Encabezado_N()
                Contenido_N()
                Spacer(modifier = Modifier.padding(300.dp))
            }
        }
    }
}
@Composable
fun Encabezado_N(){
    Text(text = "Estas son las novedades")
}

@Composable
fun Contenido_N(){

}

@Composable
fun Botones_N(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically){
            Button(onClick = { navController.navigate(route = ItemsNav.Inicio.ruta)}, modifier = Modifier
                .size(85.dp, 35.dp)
                .align(Alignment.Bottom), shape = RectangleShape
            ) {
                Text(text = "Inicio")
            }
            Button(onClick = { navController.navigate(route = ItemsNav.Dispositivos.ruta) }, modifier = Modifier
                .size(85.dp, 35.dp)
                .align(Alignment.Bottom), shape = RectangleShape
            ) {
                Text(text = "Dispositivos")
            }
            Button(onClick = { }, modifier = Modifier
                .size(85.dp, 35.dp)
                .align(Alignment.Bottom), shape = RectangleShape
            ) {
                Text(text = "Novedades")
            }
            Button(onClick = { navController.navigate(route = ItemsNav.Cuenta.ruta) }, modifier = Modifier
                .size(85.dp, 35.dp)
                .align(Alignment.Bottom), shape = RectangleShape
            ) {
                Text(text = "Cuenta")
            }
        }
    }
}