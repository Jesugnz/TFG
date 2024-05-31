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
import com.example.tfg.clases.Datos_DE
import com.example.tfg.clases.Dispositivos_Electrodomesticos
import com.example.tfg.clases.Estado_Electrodomesticos
import com.example.tfg.modelos.Datos_Dispositivos_Elec

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaDispositivoElec(
    navController: NavHostController,
    id: String,
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
                RecogerDatosPantallaDE(datos_de, navController, id)
            }
        }
    }
}

@Composable
private fun RecogerDatosPantallaDE(
    datosDe: Datos_Dispositivos_Elec,
    navController: NavHostController,
    id: String
) {
    //Esta función se encarga de recoger los datos, manteniendo la pantalla en Carga mientras los recoge y una vez los tiene, los muestra por pantalla con el caso Exito
    when(val result = datosDe.state.value){
        is Estado_Electrodomesticos.Cargando -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = "Cargando los dispositivos")
                    Spacer(modifier = Modifier.padding(16.dp))
                    LinearProgressIndicator()
                }
            }
        }
        is Estado_Electrodomesticos.Exito -> {
            Encabezado_Dispositivo_Elec(navController, id, result.datos)
            Spacer(modifier = Modifier.padding(55.dp))
            Ficha_Elec(result.datos, id)
        }
        is Estado_Electrodomesticos.Fallo -> {}
        is Estado_Electrodomesticos.Vacio -> {}
    }
}


@SuppressLint("UnrememberedMutableState")
@Composable
private fun Encabezado_Dispositivo_Elec(
    navController: NavHostController,
    id: String,
    getDatos: Dispositivos_Electrodomesticos
) {
    val composicion = mutableStateOf(Datos_DE())
    for (i in getDatos.datos){
        if(i.id_de.equals("2kkengbP2qBxeXzmTOgU"))
            composicion.value = i
    }
    var on by rememberSaveable {  mutableStateOf(composicion.value.encendido_de) }
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
            text = composicion.value.nombre_de,
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
            text = composicion.value.ubicacion_de,
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
                    text = "${composicion.value.kwh_de} Kw/h",
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
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun Ficha_Elec(getDatos: Dispositivos_Electrodomesticos, id: String) {
    var composicion = mutableStateOf(Datos_DE())
    for (i in getDatos.datos){
        if(i.id_de.equals("2kkengbP2qBxeXzmTOgU"))
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
            Text(text = " Id: ${composicion.value.id_de}")
            Text(text = " Nombre: ${composicion.value.nombre_de}")
            Text(text = " Consumo por hora: ${composicion.value.kwh_de}")
            Text(text = " Ubicación: ${composicion.value.ubicacion_de}")
            if(composicion.value.nombre_de.equals("Lavadora")){
                Text(text = " Carcteristicas: \n" +
                        "  -Capacidad de carga -> 10 kg\n" +
                        "  -Motor Inverter -> Si\n" +
                        "  -Bloqueo infantil -> Si\n" +
                        "  -Nº de programas -> 15\n" +
                        "  -Enjuague -> Si\n" +
                        "  -Nivel de ruido del centrifugado -> 76 dB(A)")
            }
            else{
                Text(text = " Carcteristicas: \n" +
                        "  -Pantalla -> Si\n" +
                        "  -Asistente de dosis -> Si\n" +
                        "  -Media carga -> Si\n" +
                        "  -Nº de programas -> 5\n" +
                        "  -Seguridad -> Antifugas\n" +
                        "  -Bloqueo infantil -> Si")
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