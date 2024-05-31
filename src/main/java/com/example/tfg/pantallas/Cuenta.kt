package com.example.tfg.pantallas

import android.annotation.SuppressLint
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.tfg.R
import com.example.tfg.clases.Usuarios
import com.example.tfg.componentes.NavegacionInferior
import com.example.tfg.modelos.Datos_Usuarios
import com.example.tfg.navegacion.PantallaNav
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaCuenta(navController: NavHostController, datosUsuarios: Datos_Usuarios= viewModel()) {
    //Con esta lista de colores, nos encargaremos de hacer un degradado para el fondo de pantalla
    val listaColores = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.primary,
        Color(0xFFFFFFFF),
        Color(0xFFFFFFFF),
        Color(0xFFFFFFFF)
    )
    val getDatos = datosUsuarios.estado.value
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),bottomBar = { NavegacionInferior(navController) }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Degradado(isVerticalGradient = true, colors = listaColores))
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Encabezado_C()
                Contenido_C(getDatos)
                Spacer(modifier = Modifier.padding(120.dp))
                Boton_Cerrar_Sesion(navController)
                Spacer(modifier = Modifier.padding(35.dp))
                Ayuda(navController)
            }
        }
    }
}

@Composable
fun Encabezado_C() {
    //Este es el encabezado, encontramos el título de la pantalla
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(MaterialTheme.colorScheme.primary),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Cuenta",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun Contenido_C(getDatos: Usuarios) {
    //Estos TextField recogen y muestran los datos del usuario logueado
    Spacer(modifier = Modifier.padding(6.dp))
    //Nombre
    TextField(
        value = getDatos.nombre_usuario,
        onValueChange = { },
        enabled = false,
        textStyle = MaterialTheme.typography.bodyMedium,
        placeholder = { Text(text = "ID Usuario") },
        modifier = Modifier.size(325.dp, 50.dp),
        colors = TextFieldDefaults.colors(
            disabledContainerColor = Color.Transparent,
            disabledTextColor = Color.White,
            disabledPlaceholderColor = Color.Black
        )
    )
    Spacer(modifier = Modifier.padding(3.dp))
    //Email
    TextField(
        value = getDatos.email_usuario,
        onValueChange = { },
        enabled = false,
        textStyle = MaterialTheme.typography.bodyMedium,
        placeholder = { Text(text = "Email") },
        modifier = Modifier.size(325.dp, 50.dp),
        colors = TextFieldDefaults.colors(
            disabledContainerColor = Color.Transparent,
            disabledPlaceholderColor = Color.Black,
            disabledTextColor = Color.White
        )
    )
    Spacer(modifier = Modifier.padding(3.dp))
    //Dirección
    TextField(
        value = getDatos.direccion_usuario,
        onValueChange = { },
        enabled = false,
        textStyle = MaterialTheme.typography.bodyMedium,
        placeholder = { Text(text = "Dirección") },
        modifier = Modifier.size(325.dp, 50.dp),
        colors = TextFieldDefaults.colors(
            disabledContainerColor = Color.Transparent,
            disabledPlaceholderColor = Color.Black,
            disabledTextColor = Color.White
        )
    )
}

@Composable
private fun Boton_Cerrar_Sesion(navController: NavHostController) {
    //Este botón se encarga de cerrar la sesión actual del usuario y redirigir a la pantalla de inicio
    Button(
        onClick = {
            FirebaseAuth.getInstance().signOut()
            navController.navigate(route = PantallaNav.PantallaRegistro.name)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = Color.White
        ),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.size(300.dp, 50.dp),
        enabled = true
    ) {
        Text(text = "Cerrar Sesión", style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun Ayuda(navController: NavHostController) {
    //Este icono te desplaza a la pantalla de ayuda
    Row {
        Spacer(modifier = Modifier.padding(150.dp))
        IconButton(
            onClick = { navController.navigate(route = PantallaNav.PantallaAyuda.name) },
            modifier = Modifier.padding(20.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ayuda),
                contentDescription = "Centro de ayuda",
                modifier = Modifier.size(45.dp),
                tint = Color.Black
            )
        }
        Spacer(modifier = Modifier.padding(2.dp))
    }
}

@Composable
private fun Degradado(isVerticalGradient: Boolean, colors: List<Color>): Brush {
    val endOffset = if(isVerticalGradient){
        Offset(0f, Float.POSITIVE_INFINITY)
    }else{
        Offset(Float.POSITIVE_INFINITY, 0f)
    }
    return Brush.linearGradient(colors = colors, start = Offset.Zero, end = endOffset)
}
