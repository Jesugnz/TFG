package com.example.tfg.pantallas

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.example.tfg.clases.Datos_DE
import com.example.tfg.clases.Datos_DS
import com.example.tfg.clases.Dispositivos_Electrodomesticos
import com.example.tfg.clases.Dispositivos_Seguridad
import com.example.tfg.clases.Datos_DT
import com.example.tfg.clases.Dispositivos_Temperatura
import com.example.tfg.clases.Estado_Electrodomesticos
import com.example.tfg.clases.Estado_Seguridad
import com.example.tfg.clases.Estado_Temperatura
import com.example.tfg.componentes.NavegacionInferior
import com.example.tfg.modelos.Datos_Dispositivos_Elec
import com.example.tfg.modelos.Datos_Dispositivos_Seg
import com.example.tfg.modelos.Datos_Dispositivos_Temp
import com.example.tfg.navegacion.PantallaNav

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaDispositivo(
    navController: NavHostController,
    datos_dt: Datos_Dispositivos_Temp = viewModel(),
    datos_ds: Datos_Dispositivos_Seg = viewModel(),
    datos_de: Datos_Dispositivos_Elec = viewModel()
) {
    //Con esta lista de colores, nos encargaremos de hacer un degradado para el fondo de pantalla
    val listaColores = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.primary,
        Color(0xFFFFFFFF),
        Color(0xFFFFFFFF),
        Color(0xFFFFFFFF)
    )
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(Color.White), bottomBar = { NavegacionInferior(navController) }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Degradado(isVerticalGradient = true, colors = listaColores))
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Encabezado_D()
                Spacer(modifier = Modifier.padding(25.dp))
                RecogerDatosDT(datos_dt, datos_ds, datos_de, navController)
            }
        }
    }
}

@Composable
private fun RecogerDatosDT(
    datosDt: Datos_Dispositivos_Temp,
    datosDs: Datos_Dispositivos_Seg,
    datos_De: Datos_Dispositivos_Elec,
    navController: NavHostController
) {
    //Esta función se encarga de recoger los datos, manteniendo la pantalla en Carga mientras los recoge y una vez los tiene, los muestra por pantalla con el caso Exito
    when(val result = datosDt.state.value){
        is Estado_Temperatura.Cargando -> {
            when(val result2 = datosDs.state.value){
                is Estado_Seguridad.Cargando -> {
                    when(val result3 = datos_De.state.value){
                        is Estado_Electrodomesticos.Cargando -> {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                                Column(horizontalAlignment = Alignment.CenterHorizontally){
                                    Text(text = "Cargando los dispositivos")
                                    Spacer(modifier = Modifier.padding(16.dp))
                                    LinearProgressIndicator()
                                }
                            }
                        }
                        is Estado_Electrodomesticos.Exito -> {}
                        is Estado_Electrodomesticos.Fallo -> {}
                        is Estado_Electrodomesticos.Vacio -> {}
                    }
                }
                is Estado_Seguridad.Exito -> {}
                is Estado_Seguridad.Fallo -> {}
                is Estado_Seguridad.Vacio -> {}
            }
        }
        is Estado_Temperatura.Exito -> {
            when(val result2 = datosDs.state.value){
                Estado_Seguridad.Cargando -> {
                    when(val result3 = datos_De.state.value){
                        Estado_Electrodomesticos.Cargando -> {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                                Column(horizontalAlignment = Alignment.CenterHorizontally){
                                    Text(text = "Cargando los dispositivos")
                                    Spacer(modifier = Modifier.padding(16.dp))
                                    LinearProgressIndicator()
                                }
                            }
                        }
                        is Estado_Electrodomesticos.Exito -> {}
                        is Estado_Electrodomesticos.Fallo -> {}
                        is Estado_Electrodomesticos.Vacio -> {}
                    }
                }
                is Estado_Seguridad.Exito -> {
                    when(val result3 = datos_De.state.value){
                        Estado_Electrodomesticos.Cargando -> {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                                Column(horizontalAlignment = Alignment.CenterHorizontally){
                                    Text(text = "Cargando los dispositivos")
                                    Spacer(modifier = Modifier.padding(16.dp))
                                    LinearProgressIndicator()
                                }
                            }
                        }
                        is Estado_Electrodomesticos.Exito -> {
                            Dispositivos_Usuario(result.datos, result2.datos, result3.datos, navController)
                        }
                        is Estado_Electrodomesticos.Fallo -> {}
                        is Estado_Electrodomesticos.Vacio -> {}
                    }
                }
                is Estado_Seguridad.Fallo -> {}
                is Estado_Seguridad.Vacio -> {}
            }
        }
        is Estado_Temperatura.Fallo -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = result.mensaje)
            }
        }
        is Estado_Temperatura.Vacio -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = "No se han encontrado datos")
            }
        }
    }
}

@Composable
private fun Encabezado_D() {
    //Este es el encabezado de la pantalla
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(MaterialTheme.colorScheme.primary),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Dispositivos",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
private fun Dispositivos_Usuario(
    dt: Dispositivos_Temperatura,
    ds: Dispositivos_Seguridad,
    de: Dispositivos_Electrodomesticos,
    navController: NavHostController
) {
    //Una vez se recogen los datos, se añaden a listas independientes y se les pasa a la siguiente función
    val dispositivos_dt = mutableListOf<Datos_DT>()
    for (d in dt.datos){
        dispositivos_dt.add(d)
    }
    val dispositivos_ds = mutableListOf<Datos_DS>()
    for (s in ds.datos){
        dispositivos_ds.add(s)
    }
    val dispositivos_de = mutableListOf<Datos_DE>()
    for (c in de.datos){
        dispositivos_de.add(c)
    }

    Column {
        Row {
            Spacer(modifier = Modifier.padding(2.dp))
            Text(
                text = "Dispositivos de temperatura",
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
            for (i in dispositivos_dt) {
                Card_Dispositivo_Temp(i, navController)
            }
        }
        Spacer(modifier = Modifier.padding(5.dp))
        Row {
            Spacer(modifier = Modifier.padding(2.dp))
            Text(
                text = "Dispositivos de Seguridad",
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
            for (j in dispositivos_ds) {
                Card_Dispositivo_Seg(j, navController)
            }
        }
        Spacer(modifier = Modifier.padding(5.dp))
        Row {
            Spacer(modifier = Modifier.padding(2.dp))
            Text(
                text = "Electrodomésticos",
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
            for (k in dispositivos_de) {
                Card_Dispositivo_Elec(k, navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Card_Dispositivo_Temp(
    datos: Datos_DT,
    navController: NavHostController
) {
    //Los dispositivos de temperatura, se pasan uno a uno y sus datos se utilizan para completar una Card
    Card(
        modifier = Modifier
            .padding(10.dp)
            .width(320.dp)
            .height(100.dp)
            .clickable { navController.navigate(PantallaNav.PantallaDispositivoTemp.name + "/${datos.id_dt}") }) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row {
                Spacer(modifier = Modifier.padding(2.dp))
                Column {
                    Spacer(modifier = Modifier.padding(15.dp))
                    if (datos.nombre_dt.equals("A. Acondicionado")) {
                        Icon(
                            painter = painterResource(id = R.drawable.aire_acondicionado),
                            contentDescription = "item.titulo"
                        )
                    } else if (datos.nombre_dt.equals("Calefacción")) {
                        Icon(
                            painter = painterResource(id = R.drawable.calefacci_n),
                            contentDescription = "item.titulo"
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(18.dp))
                Column {
                    Spacer(modifier = Modifier.padding(15.dp))
                    Text(text = datos.nombre_dt, style = MaterialTheme.typography.bodyMedium)
                    Text(text = datos.ubicacion_dt, style = MaterialTheme.typography.bodyMedium)
                }
                Spacer(modifier = Modifier.padding(15.dp))
                Column {
                    Spacer(modifier = Modifier.padding(20.dp))
                    if (datos.encendido_dt) {
                        Text(
                            text = "${datos.kwh_dt} kw/h",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    } else {
                        Text(text = "0.0 kw/h", style = MaterialTheme.typography.bodyMedium)
                    }

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Card_Dispositivo_Seg(
    datos: Datos_DS,
    navController: NavHostController
) {
    //Los dispositivos de seguridad, se pasan uno a uno y sus datos se utilizan para completar una Card
    Card(
        modifier = Modifier
            .padding(10.dp)
            .width(320.dp)
            .height(100.dp)
            .clickable {
                navController.navigate(PantallaNav.PantallaDispositivoSeg.name + "/${datos.id_ds}")
            }) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row {
                Spacer(modifier = Modifier.padding(2.dp))
                Column {
                    Spacer(modifier = Modifier.padding(15.dp))
                    if (datos.nombre_ds.equals("Cámara")) {
                        Icon(
                            painter = painterResource(id = R.drawable.camara),
                            contentDescription = "item.titulo"
                        )
                    } else if (datos.nombre_ds.equals("Sensor Puerta")) {
                        Icon(
                            painter = painterResource(id = R.drawable.sensor_puerta),
                            contentDescription = "item.titulo"
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(18.dp))
                Column {
                    Spacer(modifier = Modifier.padding(15.dp))
                    Text(text = datos.nombre_ds, style = MaterialTheme.typography.bodyMedium)
                    Text(text = datos.ubicacion_ds, style = MaterialTheme.typography.bodyMedium)
                }
                Spacer(modifier = Modifier.padding(20.dp))
                Column {
                    Spacer(modifier = Modifier.padding(20.dp))
                    if (datos.encendido_ds) {
                        Text(
                            text = "${datos.kwh_ds} kw/h",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    } else {
                        Text(text = "0.0 kw/h", style = MaterialTheme.typography.bodyMedium)
                    }

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Card_Dispositivo_Elec(
    datos: Datos_DE,
    navController: NavHostController
) {
    //Los dispositivos electrodomésticos, se pasan uno a uno y sus datos se utilizan para completar una Card
    Card(
        modifier = Modifier
            .padding(10.dp)
            .width(320.dp)
            .height(100.dp)
            .clickable { navController.navigate(PantallaNav.PantallaDispositivoElec.name + "/${datos.id_de}") }) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row {
                Spacer(modifier = Modifier.padding(2.dp))
                Column {
                    Spacer(modifier = Modifier.padding(15.dp))
                    if (datos.nombre_de.equals("Lavadora")) {
                        Icon(
                            painter = painterResource(id = R.drawable.lavadora),
                            contentDescription = "item.titulo"
                        )
                    } else if (datos.nombre_de.equals("Lavavajillas")) {
                        Icon(
                            painter = painterResource(id = R.drawable.lavavajillas),
                            contentDescription = "item.titulo"
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(18.dp))
                Column {
                    Spacer(modifier = Modifier.padding(15.dp))
                    Text(text = datos.nombre_de, style = MaterialTheme.typography.bodyMedium)
                    Text(text = datos.ubicacion_de, style = MaterialTheme.typography.bodyMedium)
                }
                Spacer(modifier = Modifier.padding(30.dp))
                Column {
                    Spacer(modifier = Modifier.padding(20.dp))
                    if (datos.encendido_de) {
                        Text(
                            text = "${datos.kwh_de} kw/h",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    } else {
                        Text(text = "0.0 kw/h", style = MaterialTheme.typography.bodyMedium)
                    }

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