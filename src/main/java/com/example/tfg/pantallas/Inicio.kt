package com.example.tfg.pantallas

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tfg.componentes.NavegacionInferior
import com.example.tfg.navegacion.NavegacionApp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaInicio(navController: NavHostController) {
    Scaffold(bottomBar = { NavegacionInferior(navController)}){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Encabezado()
                Spacer(modifier = Modifier.padding(25.dp))
                Contenido()
                Spacer(modifier = Modifier.padding(300.dp))
            }
        }
    }
}

@Composable
fun Encabezado() {
    Text(
        text = "Bienvenido",
        color = Color.Black,
        fontFamily = FontFamily.Cursive,
        fontSize = 20.sp
    )
}

@Composable
fun Contenido() {
    Card {
        Text(
            text = "Este es su consumo actual: ",
            modifier = Modifier.padding(16.dp),
            fontFamily = FontFamily.SansSerif,
            fontStyle = FontStyle.Normal,
            fontSize = 16.sp
        )
    }
}