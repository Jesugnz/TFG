package com.example.tfg.pantallas

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.tfg.clases.Noticias
import com.example.tfg.componentes.NavegacionInferior
import coil.compose.rememberAsyncImagePainter
import com.example.tfg.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaNovedades(navController: NavHostController) {
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
        .background(Color.White),bottomBar = { NavegacionInferior(navController) }) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(brush = Degradado(isVerticalGradient = true, colors = listaColores))) {//Aquí pasamos la lista de colores para la función de degradado
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                Encabezado_N()
                Contenido_N()
                Spacer(modifier = Modifier.padding(20.dp))
            }
        }
    }
}
@Composable
private fun Encabezado_N(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(MaterialTheme.colorScheme.primary),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Noticias",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
private fun Contenido_N(){
    val noticias = listOf(
        Noticias(
            imagen =  R.drawable.aire_acondicionado_noticia,
            titulo = "Como ahorrar energía si usas aire acondicionado",
            link = "https://www.madridiario.es/como-ahorrar-energia-si-usas-aire-acondicionado",
            provedor = "Madriddiario",
            fecha = "4 de Mayo del 2024"
        ),
        Noticias(
            imagen = R.drawable.confort_a_medida,
            titulo = "La domótica, confort a medida",
            link = "https://www.noticiasdegipuzkoa.eus/ejes-de-nuestra-economia/2024/04/30/domotica-confort-medida-8179316.html",
            provedor = "Noticias de Gipuzkoa",
            fecha = "30 de Abril del 2024"
        ),
        Noticias(
            imagen = R.drawable.camara_domo,
            titulo = "Las cinco claves antes de poner cámaras en casa",
            link = "https://www.elespanol.com/elandroidelibre/noticias-y-novedades/20240430/claves-poner-camaras-casa-puedes-vigilarla-movil-vas-ir-puente/848165350_0.html",
            provedor = "El Español",
            fecha = "30 de Abril del 2024"
        ),
        Noticias(
            imagen = R.drawable.blink_mini,
            titulo = "Blink Mini 2",
            link = "https://www.xataka.com/domotica-1/blink-mini-2-camara-videovigilancia-amazon-vendida-ahora-tambien-para-exteriores",
            provedor = "Xataka",
            fecha = "30 de Abril del 2024"
        ),
        Noticias(
            imagen = R.drawable.comelit,
            titulo = "Comelit adquiere el 98% del capital social de Ingenium",
            link = "https://www.casadomo.com/2024/05/09/comelit-adquiere-98-capital-social-ingenium-ampliar-oferta-automatizacion-edificios",
            provedor = "CASADOMO",
            fecha = "9 de Mayo del 2024"
        ),
        Noticias(
            imagen = R.drawable.claves_camaras,
            titulo = "Las cinco claves antes de poner cámaras en casa",
            link = "https://www.merca20.com/la-casa-inteligente-a-tu-alcance-con-la-domotica-de-netzhome/",
            provedor = "Merca 2.0",
            fecha = "8 de Mayo del 2024"
        ),
        Noticias(
            imagen = R.drawable.home_asistant,
            titulo = "Así puedes subir tus copias de seguridad de Home Assistant a Google Drive",
            link = "https://www.redeszone.net/tutoriales/domotica/home-assistant-copias-seguridad-google-drive/",
            provedor = "Redes Zone",
            fecha = "8 de Mayo del 2024"
        ),
        Noticias(
            imagen = R.drawable.movilidad,
            titulo = "Domótica para fomentar la independencia en movilidad reducida",
            link = "https://www.latribunadetoledo.es/noticia/z8578a7fe-eb90-ad56-861cce25a17dc9f1/202308/domotica-para-fomentar-la-independencia-en-movilidad-reducida",
            provedor = "La Tribuna de Toledo",
            fecha = "23 de Agosto del 2023"
        ),
        Noticias(
            imagen = R.drawable.blanco,
            titulo = "",
            link = "",
            provedor = "",
            fecha = ""
        )
    )
    Column {
        LazyColumn {
            items(items= noticias){
                Panel_Noticias(noticias = it)
                if (noticias.indexOf(it) != noticias.size) {
                    Divider(modifier = Modifier.padding(horizontal = 18.dp))
                }
            }
        }
    }
}

@Composable
private fun Panel_Noticias(noticias: Noticias){
    val uriHandler = LocalUriHandler.current
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable {
            uriHandler.openUri(noticias.link)
        }) {
        Image(
            painter = rememberAsyncImagePainter(model = noticias.imagen),
            contentScale = ContentScale.Crop,
            contentDescription = noticias.titulo,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .size(100.dp)
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Box(modifier = Modifier.fillMaxHeight(0.5f)) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "${noticias.provedor} ", fontWeight = FontWeight.Light, color = Color.Black)
                    Text(text = noticias.titulo, modifier = Modifier.fillMaxHeight(0.25f), color = Color.Black)
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Text(
                    text = noticias.fecha,
                    fontWeight = FontWeight.ExtraLight,
                    fontStyle = FontStyle.Italic,
                    color = Color.Black
                )
            }
        }
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