package com.example.tfg.pantallas

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.example.tfg.R
import com.example.tfg.clases.Datos_DE
import com.example.tfg.clases.Datos_DS
import com.example.tfg.clases.Datos_DT
import com.example.tfg.clases.Dispositivos_Electrodomesticos
import com.example.tfg.clases.Dispositivos_Seguridad
import com.example.tfg.clases.Dispositivos_Temperatura
import com.example.tfg.clases.Estado_Electrodomesticos
import com.example.tfg.clases.Estado_Seguridad
import com.example.tfg.clases.Estado_Temperatura
import com.example.tfg.componentes.NavegacionInferior
import com.example.tfg.modelos.Datos_Dispositivos_Elec
import com.example.tfg.modelos.Datos_Dispositivos_Seg
import com.example.tfg.modelos.Datos_Dispositivos_Temp
import com.example.tfg.navegacion.PantallaNav
import java.math.BigDecimal
import java.math.RoundingMode

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaInicio(
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
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        bottomBar = { NavegacionInferior(navController) },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Degradado(isVerticalGradient = true, colors = listaColores))
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Encabezado()
                Spacer(modifier = Modifier.padding(5.dp))
                RecogerDatosDT(datos_dt, datos_ds, datos_de)
                Spacer(modifier = Modifier.padding(5.dp))
                FilaGraficas(navController)
            }
        }
    }
}

@Composable
private fun Encabezado() {
    //Este es el encabezado de la pantalla
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Inicio",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
private fun RecogerDatosDT(
    datosDt: Datos_Dispositivos_Temp,
    datosDs: Datos_Dispositivos_Seg,
    datosDe: Datos_Dispositivos_Elec
) {
    //Esta función se encarga de recoger los datos, manteniendo la pantalla en Carga mientras los recoge y una vez los tiene, los muestra por pantalla con el caso Exito
    when (val result = datosDt.state.value) {
        is Estado_Temperatura.Cargando -> {
            when (val result2 = datosDs.state.value) {
                Estado_Seguridad.Cargando -> {
                    when (val result3 = datosDe.state.value) {
                        Estado_Electrodomesticos.Cargando -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(text = "Cargando los dispositivos")
                                    Spacer(modifier = Modifier.padding(16.dp))
                                    LinearProgressIndicator()
                                }
                            }
                        }

                        is Estado_Electrodomesticos.Exito -> {}
                        is Estado_Electrodomesticos.Fallo -> {}
                        Estado_Electrodomesticos.Vacio -> {}
                    }
                }

                is Estado_Seguridad.Exito -> {}
                is Estado_Seguridad.Fallo -> {}
                Estado_Seguridad.Vacio -> {}
            }
        }

        is Estado_Temperatura.Exito -> {
            when (val result2 = datosDs.state.value) {
                Estado_Seguridad.Cargando -> {
                    when (val result3 = datosDe.state.value) {
                        Estado_Electrodomesticos.Cargando -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(text = "Cargando los dispositivos")
                                    Spacer(modifier = Modifier.padding(16.dp))
                                    LinearProgressIndicator()
                                }
                            }
                        }

                        is Estado_Electrodomesticos.Exito -> {}
                        is Estado_Electrodomesticos.Fallo -> {}
                        Estado_Electrodomesticos.Vacio -> {}
                    }
                }

                is Estado_Seguridad.Exito -> {
                    when (val result3 = datosDe.state.value) {
                        Estado_Electrodomesticos.Cargando -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(text = "Cargando los dispositivos")
                                    Spacer(modifier = Modifier.padding(16.dp))
                                    LinearProgressIndicator()
                                }
                            }
                        }

                        is Estado_Electrodomesticos.Exito -> {
                            Contenido(result.datos, result2.datos, result3.datos)
                        }

                        is Estado_Electrodomesticos.Fallo -> {}
                        Estado_Electrodomesticos.Vacio -> {}
                    }
                }

                is Estado_Seguridad.Fallo -> {}
                Estado_Seguridad.Vacio -> {}
            }
        }

        is Estado_Temperatura.Fallo -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = result.mensaje)
            }
        }

        is Estado_Temperatura.Vacio -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "No se han encontrado datos")
            }
        }
    }
}

@Composable
private fun Contenido(
    datos: Dispositivos_Temperatura,
    datos1: Dispositivos_Seguridad,
    datos2: Dispositivos_Electrodomesticos
) {
    //Esta función se encarga de mostrar un gif simulando el hogar en funcionamiento, y un pequeño cuadro que muestre el consumo que hay es ese momento
    val dispositivos_dt = mutableListOf<Datos_DT>()
    var kw1 = 0.0
    for (d in datos.datos){
        dispositivos_dt.add(d)
        for (d1 in dispositivos_dt){
            if(d1.encendido_dt){kw1 += d1.kwh_dt}
        }
    }
    val dispositivos_ds = mutableListOf<Datos_DS>()
    var kw2 = 0.0
    for (s in datos1.datos){
        dispositivos_ds.add(s)
        for (d2 in dispositivos_ds){
            if(d2.encendido_ds){kw2 += d2.kwh_ds}
        }
    }
    val dispositivos_de = mutableListOf<Datos_DE>()
    var kw3 = 0.0
    for (c in datos2.datos){
        dispositivos_de.add(c)
        for (d3 in dispositivos_de){
            if (d3.encendido_de){kw3 += d3.kwh_de}
        }
    }
    var kw_total = kw1 + kw2 + kw3
    val kw_total_redondeado = BigDecimal(kw_total).setScale(2, RoundingMode.HALF_EVEN).toDouble()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(365.dp)
    ) {
        val context = LocalContext.current
        val imageLoader = ImageLoader.Builder(context)
            .components {
                add(GifDecoder.Factory())
            }
            .build()
        val painter = rememberImagePainter(
            ImageRequest.Builder(context)
                .data(R.drawable.casa_animada_2)
                .decoderFactory(GifDecoder.Factory())
                .build(),
            imageLoader = imageLoader
        )
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
        )
        // Aquí va el contenido superpuesto
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)// Ajusta el padding según tu necesidad
        ) {
            Spacer(modifier = Modifier.padding(115.dp))
            Card(
                modifier = Modifier
                    .width(75.dp)
                    .height(45.dp),
                border = BorderStroke(2.dp, Color.Black)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("$kw_total_redondeado Kw", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Composable
private fun FilaGraficas(navController: NavHostController) {
    //Esta es la fila que englobará los tres tipos de gráficas
    var graficas = listOf<String>("Consumo Diario", "Consumo Semanal", "Consumo Mensual")
    Column(modifier = Modifier.fillMaxSize()) {
        LazyRow {
            items(items = graficas) { item ->
                Graficas(item, navController)
            }
        }
    }
}


@Composable
private fun Graficas(nombre: String, navController: NavHostController) {
    //Esta función, llamada por FilaGraficas, se encargará de dar forma a sus respectivas Card
    Card(
        modifier = Modifier
            .width(340.dp)
            .height(225.dp)
            .padding(4.dp)
            .clickable {
                if (nombre.equals("Consumo Diario")) {
                    navController.navigate(PantallaNav.PantallaGraficaDiaria.name)
                } else if (nombre.equals("Consumo Semanal")) {
                    navController.navigate(PantallaNav.PantallaGraficaSemanal.name)
                } else if (nombre.equals("Consumo Mensual")) {
                    navController.navigate(PantallaNav.PantallaGraficaMensual.name)
                }
            }
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = nombre, style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.padding(2.dp))
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(185.dp)
                ) {
                    if (nombre.equals("Consumo Diario")) {
                        Image(
                            painter = painterResource(id = R.drawable.grafica_diaria),
                            contentDescription = "logo", modifier = Modifier.fillMaxSize()
                        )
                    } else if (nombre.equals("Consumo Semanal")) {
                        Image(
                            painter = painterResource(id = R.drawable.grafica_semanal),
                            contentDescription = "logo", modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.grafica_mensual),
                            contentDescription = "logo", modifier = Modifier.fillMaxSize()
                        )
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