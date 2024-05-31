package com.example.tfg.pantallas

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.tfg.R
import com.example.tfg.login.InicioViewModel
import com.example.tfg.navegacion.PantallaNav
import com.google.firebase.auth.FirebaseAuth

@Composable
fun PantallaRegistro(
    navController: NavHostController,
    viewModel: InicioViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    //Con esta lista de colores, nos encargaremos de hacer un degradado para el fondo de pantalla
    val listaColores = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.primary,
        Color(0xFFFFFFFF),
        Color(0xFFFFFFFF),
        Color(0xFFFFFFFF)
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Degradado(isVerticalGradient = true, colors = listaColores)),//Aquí poasamos la lista de colores y llamamos al método
        contentAlignment = Alignment.TopCenter
    ) {
        Cabecera(Modifier.align(Alignment.TopEnd))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(100.dp))
            Image(
                painter = painterResource(id = R.drawable.nuevo_logo_domoearth),
                contentDescription = "logo", modifier = Modifier.size(300.dp, 175.dp)
            )
            Spacer(modifier = Modifier.size(15.dp))
            val usuario = Usuario()
            Spacer(modifier = Modifier.size(25.dp))
            BotonLog(navController, usuario, LocalContext.current)
            Spacer(modifier = Modifier.size(160.dp))
            Olvido(navController)
        }
    }
}

@Composable
private fun Cabecera(modificador: Modifier) {
    val activity = LocalContext.current as Activity
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "Cerrar app",
        modifier = modificador.clickable { activity.finish() })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Usuario(): Pair<String, String> {
    var texto_usuario by rememberSaveable { mutableStateOf("") }
    TextField(
        value = texto_usuario,
        onValueChange = { texto_usuario = it },
        placeholder = { Text(text = "Usuario", style = MaterialTheme.typography.bodyMedium) },
        modifier = Modifier.size(325.dp, 50.dp),
        textStyle = MaterialTheme.typography.bodyMedium,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedTextColor = Color.Black,
            focusedTextColor = Color.Black,
            unfocusedPlaceholderColor = Color.Black,
            focusedPlaceholderColor = Color.Black
        )
    )
    Spacer(modifier = Modifier.size(17.dp))
    var texto_contraseña by rememberSaveable { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    TextField(
        value = texto_contraseña,
        onValueChange = { texto_contraseña = it },
        placeholder = { Text(text = "Contraseña", style = MaterialTheme.typography.bodyMedium) },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = Modifier.size(325.dp, 50.dp),
        textStyle = MaterialTheme.typography.bodyMedium,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedTextColor = Color.Black,
            focusedTextColor = Color.Black,
            unfocusedPlaceholderColor = Color.Black,
            focusedPlaceholderColor = Color.Black
        ),
        trailingIcon = {
            IconButton(
                onClick = { passwordVisible = !passwordVisible },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    painter = painterResource(id = if (passwordVisible) R.drawable.mostrar else R.drawable.no_mostrar),
                    contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    )

    return Pair(texto_usuario, texto_contraseña)
}


@Composable
private fun Olvido(navController: NavHostController) {
    Spacer(modifier = Modifier.size(300.dp, 20.dp))
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
private fun BotonLog(
    navController: NavHostController,
    usuario: Pair<String, String>,
    context: Context,
    viewModel: InicioViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    Button(
        onClick = {
            if (usuario.first.isNotEmpty() && usuario.second.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(usuario.first, usuario.second)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            navController.navigate(PantallaNav.PantallaInicio.name)
                        } else {
                            Toast.makeText(
                                context,
                                "Usuario o contraseña no válidos",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
            } else {
                Toast.makeText(context, "Usuario o contraseña no válidos", Toast.LENGTH_SHORT)
                    .show()
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = Color.White
        ),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.size(300.dp, 50.dp),
        enabled = true
    ) {
        Text(text = "Iniciar sesión", style = MaterialTheme.typography.bodyMedium)
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