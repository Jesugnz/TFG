package com.example.tfg.pantallas

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import com.example.tfg.R


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaAyuda(navController: NavHostController) {
    //Con esta lista de colores, nos encargaremos de hacer un degradado para el fondo de pantalla
    val listaColores = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.primary,
        Color(0xFFFFFFFF),
        Color(0xFFFFFFFF),
        Color(0xFFFFFFFF)
    )
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Degradado(isVerticalGradient = true, colors = listaColores))
        ) {
            Spacer(modifier = Modifier.padding(10.dp))
            Atras(
                Modifier
                    .align(Alignment.TopStart)
                    .size(50.dp)
                    .padding(10.dp), navController
            )
            Spacer(modifier = Modifier.padding(50.dp))
            Contenido_H()
        }
    }
}

@Composable
private fun Atras(modificador: Modifier, navController: NavHostController) {
    //Este icono nos servirá para volver a la pantalla anterior
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = "Volver",
        modifier = modificador.clickable { navController.popBackStack() },
        tint = Color.Black
    )
}

@Composable
private fun Contenido_H() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.padding(20.dp))
        Encabezado_A()
        Spacer(modifier = Modifier.padding(20.dp))
        Problemas()
        Spacer(modifier = Modifier.padding(5.dp))
        Iconos()
        Spacer(modifier = Modifier.padding(5.dp))
        Horario()
        Spacer(modifier = Modifier.padding(5.dp))
        Contacto()
        Spacer(modifier = Modifier.padding(5.dp))
        Oficinas()
        Spacer(modifier = Modifier.padding(15.dp))
        LogoDomoEarth()
    }
}

@Composable
private fun LogoDomoEarth() {
    //Este es el logo de la empresa
    Image(
        painter = painterResource(id = R.drawable.nuevo_logo_domoearth),
        contentDescription = "Domoearth",
        modifier = Modifier.size(300.dp, 400.dp)
    )
}

@Composable
private fun Encabezado_A() {
    //Este es el título de la pantalla actual
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(MaterialTheme.colorScheme.primary),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Centro de Ayuda",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
private fun Problemas() {
    //Aquí se encuentra el primer desplegable de texto
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .width(360.dp)
            .height(40.dp)
            .background(Color.Transparent)
    ) {
        Row {
            //Este es el texto que encontramos dentro del desplegable
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(360.dp)
                    .height(265.dp)
                    .background(Color.White)
                    .border(2.dp, Color.LightGray)
            ) {
                Text(
                    text = "En caso de encontrase con cualquier problema en sus dispositivos, realice las siguientes acciones:",
                    modifier = Modifier.padding(10.dp),
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "1. Compruebe la conexión de la alimentación del dispositivo y si este está encendido.",
                    modifier = Modifier.padding(10.dp),
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "2. En caso de estar encendido, reinicie el dispositivo.",
                    modifier = Modifier.padding(10.dp),
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "3. Si el dispositivo emite cualquier ruido extraño al habitual u observa una luz roja parpadeante, contacte con el equipo técnico.",
                    modifier = Modifier.padding(10.dp),
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Box(
                modifier = Modifier
                    .width(360.dp)
                    .height(200.dp)
                    .clickable { expanded = true }
                    .background(Color.Transparent)
                    .border(2.dp, Color.LightGray)
            ) {
                Row {
                    //Este es el título
                    Spacer(modifier = Modifier.padding(50.dp))
                    Text(
                        "Problemas",
                        modifier = Modifier.padding(10.dp),
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
private fun Iconos() {
    //Aquí se encuentra el segundo desplegable de texto
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .width(360.dp)
            .height(40.dp)
            .background(Color.Transparent)
    ) {
        Row {
            //Este es el texto que encontramos dentro del desplegable
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(360.dp)
                    .height(400.dp)
                    .background(Color.White)
                    .border(2.dp, Color.LightGray)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Aquí se le muestra una leyenda con los diferentes iconos y sus significados: ",
                            modifier = Modifier.padding(10.dp),
                            color = Color.Black,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.home),
                            contentDescription = "item.titulo"
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        Text(text = " -> Inicio ", modifier = Modifier.padding(10.dp), Color.Black)
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.dispositivos),
                            contentDescription = "item.titulo"
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        Text(
                            text = " -> Sus dispositivos ",
                            modifier = Modifier.padding(10.dp),
                            Color.Black
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.novedades),
                            contentDescription = "item.titulo"
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        Text(
                            text = " -> Noticias y novedades ",
                            modifier = Modifier.padding(10.dp),
                            Color.Black
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.cuenta),
                            contentDescription = "item.titulo"
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        Text(
                            text = " -> Su cuenta ",
                            modifier = Modifier.padding(10.dp),
                            Color.Black
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.aire_acondicionado),
                            contentDescription = "item.titulo"
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        Text(
                            text = " -> Aire Acondicionado ",
                            modifier = Modifier.padding(10.dp),
                            Color.Black
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.calefacci_n),
                            contentDescription = "item.titulo"
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        Text(
                            text = " -> Calefacción ",
                            modifier = Modifier.padding(10.dp),
                            Color.Black
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.camara),
                            contentDescription = "item.titulo"
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        Text(text = " -> Cámara ", modifier = Modifier.padding(10.dp), Color.Black)
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.sensor_puerta),
                            contentDescription = "item.titulo"
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        Text(
                            text = " -> Sensor de puerta ",
                            modifier = Modifier.padding(10.dp),
                            Color.Black
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.lavadora),
                            contentDescription = "item.titulo"
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        Text(
                            text = " -> Lavadora ",
                            modifier = Modifier.padding(10.dp),
                            Color.Black
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.lavavajillas),
                            contentDescription = "item.titulo"
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        Text(
                            text = " -> Lavavajillas ",
                            modifier = Modifier.padding(10.dp),
                            Color.Black
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .width(360.dp)
                    .height(40.dp)
                    .clickable { expanded = true }
                    .background(Color.Transparent)
                    .border(2.dp, Color.LightGray)
            ) {
                Row {
                    //Este es el título
                    Spacer(modifier = Modifier.padding(50.dp))
                    Text(
                        "Iconos",
                        modifier = Modifier.padding(10.dp),
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
private fun Horario() {
    //Aquí se encuentra el tercer desplegable de texto
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .width(360.dp)
            .height(40.dp)
            .background(Color.Transparent)
    ) {
        Row {
            //Este es el texto que encontramos dentro del desplegable
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(360.dp)
                    .height(175.dp)
                    .background(Color.White)
                    .border(2.dp, Color.LightGray)
            ) {
                Text(
                    text = "El horario de atención es el siguiente: ",
                    modifier = Modifier.padding(10.dp),
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Lunes a Viernes: desde las 8:30 horas, hasta las 20:00 horas ",
                    modifier = Modifier.padding(10.dp),
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Sábados: desde las 9:00 horas, hasta las 18:00 horas",
                    modifier = Modifier.padding(10.dp),
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Box(
                modifier = Modifier
                    .width(360.dp)
                    .height(40.dp)
                    .clickable { expanded = true }
                    .background(Color.Transparent)
                    .border(2.dp, Color.LightGray)
            ) {
                Row {
                    //Este es el título
                    Spacer(modifier = Modifier.padding(50.dp))
                    Text(
                        "Horario de atención",
                        modifier = Modifier.padding(10.dp),
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}


@Composable
private fun Contacto() {
    //Aquí se encuentra el cuarto desplegable de texto
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .width(360.dp)
            .height(40.dp)
            .background(Color.Transparent)
    ) {
        Row {
            //Este es el texto que encontramos dentro del desplegable
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(360.dp)
                    .height(100.dp)
                    .background(Color.White)
                    .border(2.dp, Color.LightGray)
            ) {
                Text(
                    text = "Teléfono: +34 697 547 854 ",
                    modifier = Modifier.padding(10.dp),
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Correo electrónico: emaildomoearth@gmail.com",
                    modifier = Modifier.padding(10.dp),
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Box(
                modifier = Modifier
                    .width(360.dp)
                    .height(40.dp)
                    .clickable { expanded = true }
                    .background(Color.Transparent)
                    .border(2.dp, Color.LightGray)
            ) {
                Row {
                    //Este es el título
                    Spacer(modifier = Modifier.padding(50.dp))
                    Text(
                        "Formas de contacto",
                        modifier = Modifier.padding(10.dp),
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
private fun Oficinas() {
    //Aquí se encuentra el quinto desplegable de texto
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .width(360.dp)
            .height(40.dp)
            .background(Color.Transparent)
    ) {
        Row {
            //Este es el texto que encontramos dentro del desplegable
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(360.dp)
                    .height(175.dp)
                    .background(Color.White)
                    .border(2.dp, Color.LightGray)
            ) {
                Text(
                    text = "Oficinas disponibles: ",
                    modifier = Modifier.padding(10.dp),
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "C/Almíbar nº110, Aranjuez",
                    modifier = Modifier.padding(10.dp),
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = " R. de García Prieto, 12, Santiago de Compostela",
                    modifier = Modifier.padding(10.dp),
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = " Av. Eduardo Dato, 41, Sevilla",
                    modifier = Modifier.padding(10.dp),
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Box(
                modifier = Modifier
                    .width(360.dp)
                    .height(40.dp)
                    .clickable { expanded = true }
                    .background(Color.Transparent)
                    .border(2.dp, Color.LightGray)
            ) {
                Row {
                    //Este es el título
                    Spacer(modifier = Modifier.padding(50.dp))
                    Text(
                        "Oficinas DomoEarth",
                        modifier = Modifier.padding(10.dp),
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
private fun Degradado(isVerticalGradient: Boolean, colors: List<Color>): Brush {
    val endOffset = if (isVerticalGradient) {
        Offset(0f, Float.POSITIVE_INFINITY)
    } else {
        Offset(Float.POSITIVE_INFINITY, 0f)
    }
    return Brush.linearGradient(colors = colors, start = Offset.Zero, end = endOffset)
}