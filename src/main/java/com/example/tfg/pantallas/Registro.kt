package com.example.tfg.pantallas

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tfg.R
import com.example.tfg.navegacion.PantallaNav

@Composable
fun PantallaRegistro(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.TopCenter
    ) {
        Cabecera(Modifier.align(Alignment.TopEnd))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(160.dp))
            Image(
                painter = painterResource(id = R.drawable.domoearth),
                contentDescription = "logo", modifier = Modifier.size(300.dp, 175.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))
            val usuario = Usuario()
            Spacer(modifier = Modifier.size(15.dp))
            BotonLog(navController, usuario, LocalContext.current)
            Spacer(modifier = Modifier.size(12.dp))
            Divider(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp), color = Color.LightGray)
            Spacer(modifier = Modifier.size(12.dp))
            Olvido(navController)
            /*Row(verticalAlignment = Alignment.CenterVertically){
                Divider(modifier = Modifier
                    .size(170.dp, 2.dp)
                    .padding(end = 20.dp), color = Color.LightGray)
                Text(text = " OR ")
                Divider(modifier = Modifier
                    .size(170.dp, 2.dp)
                    .padding(start = 20.dp), color = Color.LightGray)
            }*/
        }
    }
}

@Composable
fun Cabecera(modificador: Modifier) {
    val activity = LocalContext.current as Activity
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "Cerrar app",
        modifier = modificador.clickable { activity.finish() })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Usuario(): Pair<String, String> {
    var texto_usuario by rememberSaveable { mutableStateOf("") }
    TextField(
        value = texto_usuario,
        onValueChange = { texto_usuario = it },
        placeholder = { Text(text = "Usuario") },
        modifier = Modifier.size(325.dp, 50.dp)
    )
    Spacer(modifier = Modifier.size(17.dp))
    var texto_contraseña by rememberSaveable { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    TextField(
        value = texto_contraseña,
        onValueChange = { texto_contraseña = it },
        placeholder = { Text(text = "Contraseña") },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = Modifier.size(325.dp, 50.dp),
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
fun Olvido(navController: NavHostController) {
    Spacer(modifier = Modifier.size(145.dp, 20.dp))
    Button(onClick = { navController.navigate(route = PantallaNav.PantallaAyuda.name)}, colors = ButtonDefaults.buttonColors(
        containerColor = Color.Cyan,
        contentColor = Color.White
    ), shape = MaterialTheme.shapes.large, modifier = Modifier.size(300.dp, 50.dp), enabled = true) {
        Text(text = "Help Center")
    }
}

@Composable
fun BotonLog(navController: NavHostController, usuario: Pair<String, String>, context : Context) {
    val usuarioValido = "jesus"
    val contraseñaValida = "1234"
    Button(
        onClick = {
            if (usuario.first.trim() == usuarioValido && usuario.second == contraseñaValida) {
            navController.navigate(PantallaNav.PantallaInicio.name)
            } else {
                Toast.makeText(context, "Usuario o contraseña no válidos", Toast.LENGTH_SHORT).show()
            }
        }, colors = ButtonDefaults.buttonColors(
            containerColor = Color.Cyan,
            contentColor = Color.White
        ), shape = MaterialTheme.shapes.large, modifier = Modifier.size(300.dp, 50.dp), enabled = true
    ) {
        Text(text = "Iniciar sesión")
    }
}