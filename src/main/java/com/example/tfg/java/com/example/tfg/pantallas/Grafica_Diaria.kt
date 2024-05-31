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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.tfg.clases.Datos_D
import com.example.tfg.clases.Estado_Diario
import com.example.tfg.clases.Grafica_D
import com.example.tfg.modelos.Datos_Grafica_D
import com.github.tehras.charts.bar.BarChart
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.renderer.label.SimpleValueDrawer

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaGraficaDiaria(navController: NavHostController, datos_dia : Datos_Grafica_D = viewModel()) {
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
                Encabezado_Diaria(navController)
                RecogerDatosD(datos_dia)
                Spacer(modifier = Modifier.padding(10.dp))
                Contenido_GD()
            }
        }
    }
}

@Composable
private fun RecogerDatosD(datosDia: Datos_Grafica_D) {
    //Esta función se encarga de recoger los datos, manteniendo la pantalla en Carga mientras los recoge y una vez los tiene, los muestra por pantalla con el caso Exito
    when(val result = datosDia.state.value){
        is Estado_Diario.Cargando -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = "Cargando las datos mensuales")
                    Spacer(modifier = Modifier.padding(16.dp))
                    LinearProgressIndicator()
                }
            }
        }
        is Estado_Diario.Exito -> {
            Barras(result.datos)
        }
        is Estado_Diario.Fallo -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = result.mensaje)
            }
        }
        is Estado_Diario.Vacio -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = "No se han encontrado datos")
            }
        }
    }
}

@Composable
private fun Encabezado_Diaria(navController: NavHostController) {
    //Este es el encabezado de la pantalla
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
            text = "Consumo       Diario",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
private fun Barras(result: Grafica_D) {
    //Aquí los datos recogidos se sacan fuera del array y se añaden de forma independiente en la gráfica
    var datos = mutableListOf<Datos_D>()
    for (i in result.datos){
        datos.add(i)
    }
    var barras = ArrayList<BarChartData.Bar>()
    datos.mapIndexed { index, datos ->
        barras.add(
            BarChartData.Bar(
                label = datos.hora,
                value = datos.consumo.toString().toFloat(),
                color = Color_Consumo(datos.consumo.toString().toFloat())
            )
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        BarChart(
            barChartData = BarChartData(bars = barras),
            modifier = Modifier
                .padding(horizontal = 40.dp, vertical = 40.dp)
                .height(300.dp)
                .width(900.dp),
            labelDrawer = SimpleValueDrawer(drawLocation = SimpleValueDrawer.DrawLocation.XAxis)
        )
    }
}

private fun Color_Consumo(consumo: Float): Color {
    //Esta función se encarga de asignar un color dependiendo del consumo que se realice
    if (consumo in 0f..6.31f) {
        return Color(0xFF006633)
    } else if (consumo in 6.311f..14.15f) {
        return Color(0xFFFFCC33)
    } else {
        return Color(0xFF993333)
    }
}

@Composable
private fun Contenido_GD() {
    //Aquí se muestra un pequeño texto
    Card(
        modifier = Modifier
            .width(340.dp)
            .height(150.dp)
            .padding(4.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Este es el gráfico de su consumo durante el día actual. En caso de superar los 14.15 kw/h, sus barras se mostrarán en color rojo, en caso de ser el consumo inferior a este y hasta los 6.32, estas se mostrarán de color amarillo, y si es inferior, se mostrará verde.",
                color = Color.Black
            )
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