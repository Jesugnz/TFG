package com.example.tfg.pantallas

import android.annotation.SuppressLint
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.example.tfg.clases.Datos_DS
import com.example.tfg.clases.Dispositivos_Seguridad
import com.example.tfg.clases.Estado_Seguridad
import com.example.tfg.modelos.Datos_Dispositivos_Seg

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaDispositivoSeg(
    navController: NavHostController,
    id: String,
    datosSeg: Datos_Dispositivos_Seg = viewModel()
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
                RecogerDatosPantallaDS(datosSeg, navController, id)
            }
        }
    }
}

@Composable
private fun RecogerDatosPantallaDS(
    datosDs: Datos_Dispositivos_Seg,
    navController: NavHostController,
    id: String
) {
    //Esta función se encarga de recoger los datos, manteniendo la pantalla en Carga mientras los recoge y una vez los tiene, los muestra por pantalla con el caso Exito
    when(val result = datosDs.state.value){
        is Estado_Seguridad.Cargando -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = "Cargando las datos")
                    Spacer(modifier = Modifier.padding(16.dp))
                    LinearProgressIndicator()
                }
            }
        }
        is Estado_Seguridad.Exito -> {
            Encabezado_Dispositivo_Seg(navController, id, result.datos)
            Spacer(modifier = Modifier.padding(55.dp))
            Ficha_Seg(result.datos, id)
        }
        is Estado_Seguridad.Fallo -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = result.mensaje)
            }
        }
        is Estado_Seguridad.Vacio -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = "No se han encontrado datos")
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun Encabezado_Dispositivo_Seg(
    navController: NavHostController,
    id: String,
    getDatos: Dispositivos_Seguridad
) {
    //En esta función, los datos recogidos, son mostrados por la pantalla según la estructura aquí mostrada
    var composicion = mutableStateOf(Datos_DS())
    for (i in getDatos.datos){
        if(i.id_ds.equals(id))
            composicion.value = i
    }
    var on by rememberSaveable {  mutableStateOf(composicion.value.encendido_ds) }
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
                contentDescription = "item.titulo",
                modifier = Modifier.clickable { on = !on  },
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
            text = composicion.value.nombre_ds,
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
            text = composicion.value.ubicacion_ds,
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 16.dp) // Espacio adicional entre el texto y los botones
        ) {
            if(on){
                Text(
                    text = "${composicion.value.kwh_ds} Kw/h",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                    modifier = Modifier.padding(end = 16.dp) // Espacio entre el texto y los botones
                )
            }
            else{
                Text(
                    text = "0.0 Kw/h",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                    modifier = Modifier.padding(end = 16.dp) // Espacio entre el texto y los botones
                )
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun Ficha_Seg(getDatos: Dispositivos_Seguridad, id: String) {
    var composicion = mutableStateOf(Datos_DS())
    for (i in getDatos.datos){
        if(i.id_ds.equals(id))
            composicion.value = i
    }
    Card(
        modifier = Modifier
            .width(340.dp)
            .height(280.dp)
            .padding(10.dp)
    ) {
        Column {
            Spacer(modifier = Modifier.padding(1.dp))
            Text(text = " Id: ${composicion.value.id_ds}")
            Text(text = " Nombre: ${composicion.value.nombre_ds}")
            Text(text = " Consumo por hora: ${composicion.value.kwh_ds}")
            Text(text = " Ubicación: ${composicion.value.ubicacion_ds}")
            if(composicion.value.nombre_ds.equals("Cámara")){
                Text(text = " Carcteristicas: \n" +
                        "  -Calidad de imagen -> Full-HD\n" +
                        "  -Grabación vídeo -> 1080 Full HD\n" +
                        "  -Rango de frecuencia -> 50/60 Hz\n" +
                        "  -Protección sobrecalentamiento -> Sí\n" +
                        "  -Movilidad -> Rotatable\n" +
                        "  -Detector de movimiento -> Si")
            }
            else{
                Text(text = " Carcteristicas: \n" +
                        "  -Modo de funcionamiento -> Batería\n" +
                        "  -Control por voz -> Si\n" +
                        "  -Sensor Temperatura -> No\n" +
                        "  -Sensor Agua -> No\n" +
                        "  -Sensor Humedad -> No\n" +
                        "  -Sensor CO -> No")
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