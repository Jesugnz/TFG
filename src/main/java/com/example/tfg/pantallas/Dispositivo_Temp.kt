package com.example.tfg.pantallas

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.tfg.clases.Datos_DT
import com.example.tfg.clases.Dispositivos_Temperatura
import com.example.tfg.clases.Estado_Temperatura
import com.example.tfg.modelos.Datos_Dispositivos_Temp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaDispositivoTemp(
    navController: NavHostController,
    id: String,
    datosTemp: Datos_Dispositivos_Temp = viewModel()
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
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Degradado(isVerticalGradient = true, colors = listaColores))
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RecogerDatosPantallaDT(datosTemp, navController, id)
            }
        }
    }
}

@Composable
private fun RecogerDatosPantallaDT(
    datosDt: Datos_Dispositivos_Temp,
    navController: NavHostController,
    id: String
) {
    //Esta función se encarga de recoger los datos, manteniendo la pantalla en Carga mientras los recoge y una vez los tiene, los muestra por pantalla con el caso Exito
    when(val result = datosDt.state.value){
        is Estado_Temperatura.Cargando -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = "Cargando las datos mensuales")
                    Spacer(modifier = Modifier.padding(16.dp))
                    LinearProgressIndicator()
                }
            }
        }
        is Estado_Temperatura.Exito -> {
            Encabezado_Dispositivo_Temp(navController, result.datos, id)
            Spacer(modifier = Modifier.padding(45.dp))
            Ficha_Temp(result.datos, id)
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

@SuppressLint("UnrememberedMutableState", "SuspiciousIndentation")
@Composable
private fun Encabezado_Dispositivo_Temp(
    navController: NavHostController,
    datos: Dispositivos_Temperatura,
    id: String,
) {
    //En esta función, los datos recogidos, son mostrados por la pantalla según la estructura aquí mostrada
    var composicion = mutableStateOf(Datos_DT())
    for (i in datos.datos){
        if(i.id_dt.equals(id))
        composicion.value = i
    }
    var on by rememberSaveable { mutableStateOf(composicion.value.encendido_dt) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column {
            Spacer(modifier = Modifier.padding(4.dp))
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver",
                modifier = Modifier.clickable { navController.popBackStack() },
                tint = Color.Black
            )
        }
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End){
            Spacer(modifier = Modifier.padding(4.dp))
            Icon(
                painter = painterResource(id = R.drawable.encender),
                contentDescription = "Encender/Apagar",
                modifier = Modifier.clickable { on = !on },
                tint = Color.Black
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.padding(3.dp))
        Text(
            text = composicion.value.nombre_dt,
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.padding(2.dp))
        Text(
            text = composicion.value.ubicacion_dt,
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium
        )
    }
    Spacer(modifier = Modifier.padding(40.dp))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Asegura que haya algo de espacio alrededor del contenido
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var temp by rememberSaveable { mutableStateOf(composicion.value.temperatura_dt) } // Cambia getDatos.temperatura_dt por un valor de ejemplo
        if(on){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(horizontal = 16.dp) // Espacio adicional entre el texto y los botones
            ) {
                Text(
                    text = "$temp º",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                    modifier = Modifier.padding(end = 16.dp) // Espacio entre el texto y los botones
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            if (temp < 27.5) {
                                temp += 0.5
                            }
                        },
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier.size(55.dp, 55.dp),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {
                        Column(modifier= Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                            Text(text = "+", style = MaterialTheme.typography.bodyMedium, color = Color.White)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            if (temp > 16.0) {
                                temp -= 0.5
                            }
                        },
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier.size(55.dp, 55.dp),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {
                        Column(modifier= Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                            Text(text = "-", style = MaterialTheme.typography.bodyMedium, color = Color.White)
                        }
                    }
                }
            }
        }
        else{
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(horizontal = 16.dp) // Espacio adicional entre el texto y los botones
            ) {
                Text(
                    text = "0 º",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                    modifier = Modifier.padding(end = 16.dp) // Espacio entre el texto y los botones
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {},
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier.size(55.dp, 55.dp),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {
                        Column(modifier= Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                            Text(text = "+", style = MaterialTheme.typography.bodyMedium, color = Color.Black)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {},
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier.size(55.dp, 55.dp),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {
                        Column(modifier= Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                            Text(text = "-", style = MaterialTheme.typography.bodyMedium, color = Color.Black)
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun Ficha_Temp(getDatos: Dispositivos_Temperatura, id: String) {
    var composicion = mutableStateOf(Datos_DT())
    for (i in getDatos.datos){
        if(i.id_dt.equals(id))
            composicion.value = i
    }
    Card(
        modifier = Modifier
            .width(340.dp)
            .height(250.dp)
            .padding(10.dp)
    ) {
        Column {
            Spacer(modifier = Modifier.padding(1.dp))
            Text(text = " Id: ${composicion.value.id_dt}")
            Text(text = " Nombre: ${composicion.value.nombre_dt}")
            Text(text = " Consumo por hora: ${composicion.value.kwh_dt}")
            Text(text = " Ubicación: ${composicion.value.ubicacion_dt}")
            if(composicion.value.nombre_dt.equals("Calefacción")){
                Text(text = " Carcteristicas: \n" +
                        "  -Potencia de calor -> 1800 W\n" +
                        "  -Superficie máxima -> 30 m²\n" +
                        "  -Voltaje de entrada -> 220-2440 V\n" +
                        "  -Protección sobrecalentamiento -> Sí\n" +
                        "  -Ruido de unidad exterior -> 64 dB(A)\n" +
                        "  -Inteligencia Artificial -> No")
            }
            else{
                Text(text = " Carcteristicas: \n" +
                        "  -Potencia de frío -> 3.5 kW\n" +
                        "  -Potencia calor -> 3.5 kW\n" +
                        "  -Superficie máxima -> 10 m²\n" +
                        "  -Ruido de unidad interior -> 52 dB(A)\n" +
                        "  -Ruido de unidad exterior -> 64 dB(A)\n" +
                        "  -Inteligencia Artificial -> No")
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